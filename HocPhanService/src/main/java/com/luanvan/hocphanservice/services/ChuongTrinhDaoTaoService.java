package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import com.luanvan.hocphanservice.entity.HocPhan;
import com.luanvan.hocphanservice.entity.HocPhanTuChon;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.HocPhanTuChonDTO;
import com.luanvan.hocphanservice.model.Request.ChuongTrinhDaoTaoRequest;
import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.Request.CTDTDescriptionRequest;
import com.luanvan.hocphanservice.model.Request.KeHoachHocTapRequest;
import com.luanvan.hocphanservice.model.Request.NganhDTO;
import com.luanvan.hocphanservice.model.Response.ThongKeCTDT;
import com.luanvan.hocphanservice.model.Response.TinChiResponse;
import com.luanvan.hocphanservice.repository.ChuongTrinhDaoTaoRepository;
import com.luanvan.hocphanservice.repository.HocPhanRepository;
import com.luanvan.hocphanservice.repository.httpClient.NganhClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChuongTrinhDaoTaoService {

    private final ModelMapper modelMapper;
    private final ChuongTrinhDaoTaoRepository chuongTrinhDaoTaoRepository;
    private final HocPhanRepository hocPhanRepository;
    private final NganhClient nganhClient;
    private final HocPhanService hocPhanService;
    private final HocPhanTuChonService hocPhanTuChonService;

    public ThongKeCTDT getThongKeCTDT(Long id) {
        if(id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        // Nếu không có chương trình đào tạo với id này thì trả về ThongKeCTDT rỗng
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        ThongKeCTDT thongKeCTDT = new ThongKeCTDT();
        thongKeCTDT.setKhoaHoc(chuongTrinhDaoTao.getKhoaHoc());
        thongKeCTDT.setMaNganh(chuongTrinhDaoTao.getMaNganh());
        thongKeCTDT.setTongSoHocPhan(
                chuongTrinhDaoTao.getHocPhanList() != null
                        ? chuongTrinhDaoTao.getHocPhanList().size()
                        : 0L
        );
        thongKeCTDT.setTongSoTinChi(chuongTrinhDaoTaoRepository.tongTinChi(chuongTrinhDaoTao.getKhoaHoc(), chuongTrinhDaoTao.getMaNganh()));
        thongKeCTDT.setTongSoNhomTuChon(
                chuongTrinhDaoTao.getNhomHocPhanTuChon() != null
                        ? chuongTrinhDaoTao.getNhomHocPhanTuChon().size()
                        : 0L
        );
        return thongKeCTDT;
    }

    public ChuongTrinhDaoTaoRequest getCTDT(String khoaHoc, Long maNganh) {
        if (maNganh == null && khoaHoc == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh)
                .orElse(new ChuongTrinhDaoTao());
        NganhDTO nganhDTO = nganhClient.findBymaNganh(maNganh);
        log.info("nganhDTO={}", nganhDTO);
        ChuongTrinhDaoTaoRequest response = modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
        response.setTenNganh(nganhDTO.getTenNganh());
        return response;
    }

    public ChuongTrinhDaoTaoRequest create(ChuongTrinhDaoTaoRequest dto) {
        if (dto == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        if (!nganhClient.existByMaNganh(dto.getMaNganh())) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        modelMapper.typeMap(ChuongTrinhDaoTaoRequest.class, ChuongTrinhDaoTao.class)
                .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList));
        ChuongTrinhDaoTao chuongTrinhDaoTao = modelMapper.map(dto, ChuongTrinhDaoTao.class);

        List<String> maHocPhanList = dto.getHocPhanList().stream()
                .map(HocPhanDTO::getMaHp)
                .toList();

        List<HocPhan> hocPhanList = hocPhanRepository.findByMaHpIn(maHocPhanList);
        // Nếu danh sách rỗng thì vấn nhập
        if(dto.getNhomHocPhanTuChon() != null && !dto.getNhomHocPhanTuChon().isEmpty()) {
            List<HocPhanTuChon> nhomHocPhanTuChon = dto.getNhomHocPhanTuChon().stream()
                    .map(hocPhanTuChonDTO -> modelMapper.map(hocPhanTuChonDTO, HocPhanTuChon.class))
                    .toList();
            hocPhanTuChonService.creates(nhomHocPhanTuChon, chuongTrinhDaoTao.getKhoaHoc(), chuongTrinhDaoTao.getMaNganh());
            chuongTrinhDaoTao.setNhomHocPhanTuChon(nhomHocPhanTuChon);
        }
        chuongTrinhDaoTao.setHocPhanList(hocPhanList);

        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        return modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
    }


    @Transactional
    public ChuongTrinhDaoTaoRequest update(ChuongTrinhDaoTaoRequest dto) {
        if (dto == null || dto.getMaNganh() == null || dto.getKhoaHoc() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Tìm chương trình đào tạo hiện tại
        ChuongTrinhDaoTao existingCTDT = chuongTrinhDaoTaoRepository.findChuongTrinhDaoTaoByKhoaHocAndMaNganh(
                dto.getKhoaHoc(), dto.getMaNganh());
        if (existingCTDT == null) {
            throw new AppException(ErrorCode.NOTFOUND);
        }

        // Cập nhật thông tin cơ bản
        if (dto.getTenChuongTrinhDaoTao() != null) {
            existingCTDT.setTenChuongTrinhDaoTao(dto.getTenChuongTrinhDaoTao());
        }
        if (dto.getNoiDung() != null) {
            existingCTDT.setNoiDung(dto.getNoiDung());
        }
        if (dto.getTongSoTinChi() != null) {
            existingCTDT.setTongSoTinChi(dto.getTongSoTinChi());
        }
        if (dto.getTongSoTinChiTuChon() != null) {
            existingCTDT.setTongSoTinChiTuChon(dto.getTongSoTinChiTuChon());
        }

        // Cập nhật danh sách học phần nếu có
        if (dto.getHocPhanList() != null) {
            if (!dto.getHocPhanList().isEmpty()) {
                List<String> maHocPhanList = dto.getHocPhanList().stream()
                        .map(HocPhanDTO::getMaHp)
                        .toList();
                List<HocPhan> newHocPhanList = hocPhanRepository.findByMaHpIn(maHocPhanList);

                // Khởi tạo danh sách hiện có nếu null
                if (existingCTDT.getHocPhanList() == null) {
                    existingCTDT.setHocPhanList(new ArrayList<>());
                }

                // Lọc ra các học phần chưa có trong danh sách hiện tại
                List<String> existingMaHocPhan = existingCTDT.getHocPhanList().stream()
                        .map(HocPhan::getMaHp)
                        .toList();

                List<HocPhan> hocPhanToAdd = newHocPhanList.stream()
                        .filter(hp -> !existingMaHocPhan.contains(hp.getMaHp()))
                        .toList();

                // Thêm các học phần mới vào danh sách hiện có
                existingCTDT.getHocPhanList().addAll(hocPhanToAdd);

                log.info("Added {} new courses to curriculum {} (total: {})",
                        hocPhanToAdd.size(),
                        existingCTDT.getTenChuongTrinhDaoTao(),
                        existingCTDT.getHocPhanList().size());
            } else {
                // Nếu truyền danh sách rỗng thì xóa hết học phần
                existingCTDT.setHocPhanList(new ArrayList<>());
                log.info("Cleared all courses for curriculum {}", existingCTDT.getTenChuongTrinhDaoTao());
            }
        }
        // Nếu không truyền hocPhanList (null) thì giữ nguyên danh sách hiện tại

        // Xử lý nhóm học phần tự chọn với orphan removal an toàn
        if (dto.getNhomHocPhanTuChon() != null) {
            // Khởi tạo collection nếu null
            if (existingCTDT.getNhomHocPhanTuChon() == null) {
                existingCTDT.setNhomHocPhanTuChon(new ArrayList<>());
            }

            // Xóa tất cả các orphan một cách an toàn
            existingCTDT.getNhomHocPhanTuChon().clear();

            // Flush để đảm bảo orphan removal được thực hiện
            chuongTrinhDaoTaoRepository.saveAndFlush(existingCTDT);

            // Tạo các nhóm học phần tự chọn mới nếu có
            if (!dto.getNhomHocPhanTuChon().isEmpty()) {
                List<HocPhanTuChon> nhomHocPhanTuChon = dto.getNhomHocPhanTuChon().stream()
                        .map(hocPhanTuChonDTO -> {
                            HocPhanTuChon entity = hocPhanTuChonService.createFromCTDT(existingCTDT, hocPhanTuChonDTO);
                            entity.setChuongTrinhDaoTao(existingCTDT); // Đảm bảo reference được set
                            return entity;
                        })
                        .toList();

                existingCTDT.getNhomHocPhanTuChon().addAll(nhomHocPhanTuChon);
                log.info("Created {} new elective course groups", nhomHocPhanTuChon.size());
            } else {
                log.info("Cleared all elective course groups");
            }
        }
        // Nếu không truyền nhomHocPhanTuChon (null) thì giữ nguyên danh sách hiện tại

        // Lưu và trả về
        chuongTrinhDaoTaoRepository.save(existingCTDT);
        log.info("Successfully updated curriculum: {}", existingCTDT.getTenChuongTrinhDaoTao());
        return modelMapper.map(existingCTDT, ChuongTrinhDaoTaoRequest.class);
    }

    public ChuongTrinhDaoTaoRequest getByKhoaHocAndMaNganh(String khoaHoc, Long maNganh) {
        if (khoaHoc == null || khoaHoc.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        return chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh)
                .map(chuongTrinhDaoTao -> modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class))
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        chuongTrinhDaoTao.getNhomHocPhanTuChon().forEach(hocPhanTuChon -> hocPhanTuChonService.deleteById(hocPhanTuChon.getId()));
        chuongTrinhDaoTaoRepository.delete(chuongTrinhDaoTao);
    }

    @Transactional
    public void createDSHocPhanFromFile(CTDTDescriptionRequest request, MultipartFile file) {
        if (request == null || file == null || file.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        if (request.getMaNganh() == null || request.getKhoaHoc() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        boolean nganhExists = nganhClient.existByMaNganh(request.getMaNganh());
        if (!nganhExists) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        if (chuongTrinhDaoTaoRepository.existsChuongTrinhDaoTaoByKhoaHocAndMaNganh(request.getKhoaHoc(), request.getMaNganh())) {
            throw new AppException(ErrorCode.CTDT_EXISTED);
        }

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<String> maHocPhanList = new ArrayList<>();

            log.info("Starting to read course data from row: 14");

            // Bắt đầu từ dòng 14 (index 13)
            for (int i = 13; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String maHp = getCellValue(row.getCell(0)); // Cột A - Mã học phần

                if (maHp == null || maHp.isBlank() || maHp.equalsIgnoreCase("Tổng cộng")) {
                    break;
                }

                // Kiểm tra định dạng mã học phần
                if (maHp.matches(".*[a-zA-Z0-9]+.*")) {
                    maHocPhanList.add(maHp.trim());
                    log.debug("Added course code: {}", maHp.trim());
                }
            }

            log.info("Found {} course codes in file", maHocPhanList.size());

            if (maHocPhanList.isEmpty()) {
                throw new AppException("Không tìm thấy mã học phần nào trong file!", ErrorCode.INVALID_INPUT);
            }

            // Tìm học phần trong database
            List<HocPhan> hocPhanList = hocPhanRepository.findByMaHpIn(maHocPhanList);

            // Log các học phần không tìm thấy
            List<String> notFoundCourses = maHocPhanList.stream()
                    .filter(maHp -> hocPhanList.stream().noneMatch(hp -> hp.getMaHp().equals(maHp)))
                    .toList();

            if (!notFoundCourses.isEmpty()) {
                log.warn("Courses not found in database: {}", notFoundCourses);
            }

            if (hocPhanList.isEmpty()) {
                throw new AppException("Không tìm thấy học phần nào trong cơ sở dữ liệu!", ErrorCode.NOTFOUND);
            }

            // Tạo chương trình đào tạo
            modelMapper.typeMap(CTDTDescriptionRequest.class, ChuongTrinhDaoTao.class)
                    .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList));
            ChuongTrinhDaoTao chuongTrinhDaoTao = modelMapper.map(request, ChuongTrinhDaoTao.class);
            chuongTrinhDaoTao.setHocPhanList(hocPhanList);

            chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);

            log.info("Successfully created curriculum: {} with {} courses",
                    chuongTrinhDaoTao.getTenChuongTrinhDaoTao(),
                    hocPhanList.size());

        } catch (Exception e) {
            log.error("Error reading Excel file: {}", e.getMessage(), e);
            throw new AppException("Lỗi xử lý file Excel: " + e.getMessage(), ErrorCode.INVALID_INPUT);
        }
    }

    // Hàm lấy giá trị ô cell
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                double v = cell.getNumericCellValue();
                if (v == Math.floor(v)) {
                    return String.valueOf((int) v);
                } else {
                    return String.valueOf(v);
                }
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }



    public List<HocPhanDTO> getHocPhanInCTDTByKhoaHoc(String khoaHoc, Long maNganh) {
        if (khoaHoc == null || khoaHoc.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(khoaHoc.toUpperCase(), maNganh)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_REQUEST));
        return chuongTrinhDaoTao.getHocPhanList().stream().map(
                hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class)).toList();
    }

    public List<HocPhanDTO> getHocPhanNotInCTDT(List<String> hocPhanList, String khoaHoc, Long maNganh) {
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh).orElseThrow(
                () -> new AppException(ErrorCode.NOTFOUND)
        );
        List<HocPhanTuChon> nhomHocPhanTuChon = chuongTrinhDaoTao.getNhomHocPhanTuChon();
        //Lấy danh sách mã học phần trong nhóm học phần tự chọn
        if (nhomHocPhanTuChon != null && !nhomHocPhanTuChon.isEmpty()) {
            nhomHocPhanTuChon.forEach(hocPhanTuChon -> {
                if (hocPhanTuChon.getHocPhanTuChonList() != null) {
                    hocPhanTuChon.getHocPhanTuChonList().forEach(hocPhan -> {
                        if (!hocPhanList.contains(hocPhan.getMaHp())) {
                            hocPhanList.add(hocPhan.getMaHp());
                        }
                    });
                }
            });
        }

//      DS mã học phần trong CTDT
        List<String> maHocPhanInCTDT = chuongTrinhDaoTao.getHocPhanList().stream()
                .map(HocPhan::getMaHp)
                .toList();


        if (hocPhanList == null || hocPhanList.isEmpty()) {
            return chuongTrinhDaoTao.getHocPhanList().stream()
                    .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                    .collect(Collectors.toList());
        }
        List<String> maHocPhanNotInCTDT = maHocPhanInCTDT.stream().filter(
                maHocPhan -> !hocPhanList.contains(maHocPhan)).toList();

        return hocPhanService.getDSHocPhanIn(maHocPhanNotInCTDT);

    }
    public List<ChuongTrinhDaoTaoRequest> getCTDTByMaNganh(Long maNganh) {
        if (maNganh == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<ChuongTrinhDaoTao> chuongTrinhDaoTaoList = chuongTrinhDaoTaoRepository.findByMaNganh(maNganh);
        if (chuongTrinhDaoTaoList.isEmpty()) {
            return Collections.emptyList();
        }
        List<ChuongTrinhDaoTaoRequest> result = new ArrayList<>();
        //Skip HocPhanList
        chuongTrinhDaoTaoList.forEach(chuongTrinhDaoTao -> {
            ChuongTrinhDaoTaoRequest dto = modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
            dto.setHocPhanList(Collections.emptyList());
            result.add(dto);
        });
        return result;
    }

    public TinChiResponse getCountTinChiByCTDT(String khoaHoc, Long maNganh, List<KeHoachHocTapRequest> hocPhanList) {
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh).orElseThrow(
                () -> new AppException(ErrorCode.NOTFOUND)
        );
        if (hocPhanList.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        TinChiResponse tinChiResponse = new TinChiResponse();

        Long tongSoTinChi = chuongTrinhDaoTaoRepository.tongTinChi(khoaHoc, maNganh);
        log.info("Total credits in curriculum: {}", tongSoTinChi);

        Long tongSoTinChiTuChon = chuongTrinhDaoTao.getNhomHocPhanTuChon() != null
                ? chuongTrinhDaoTao.getNhomHocPhanTuChon().stream().mapToLong(HocPhanTuChon::getTinChiYeuCau).sum()
                : 0L;

        tinChiResponse.setTongSoTinChi(tongSoTinChi + tongSoTinChiTuChon);

        List<String> newHocPhanList = new LinkedList<>();
        List<String> hocPhanCaiThienList = new LinkedList<>();

        hocPhanList.forEach(dto -> {
            String maHp = dto.getMaHocPhan();
            if (dto.isHocPhanCaiThien()) {
                hocPhanCaiThienList.add(maHp);
            } else {
                newHocPhanList.add(maHp);
            }
        });

        tinChiResponse.setSoTinChiTichLuy(hocPhanRepository.countTinChiIn(newHocPhanList));
        tinChiResponse.setSoTinChiCaiThien(hocPhanRepository.countTinChiIn(hocPhanCaiThienList));
        return tinChiResponse;
    }
    @Transactional
    public ChuongTrinhDaoTaoRequest addHocPhanTuChon(ChuongTrinhDaoTaoRequest request) {
        if (request == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        List<HocPhanTuChonDTO> hocPhanTuChonList = request.getNhomHocPhanTuChon();

        if (hocPhanTuChonList == null || hocPhanTuChonList.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<HocPhanTuChon> newHocPhanTuChonList = hocPhanTuChonList.stream()
                .map(hptc -> hocPhanTuChonService.createFromCTDT(chuongTrinhDaoTao, hptc))
                .toList();

        List<HocPhanTuChon> currentList = chuongTrinhDaoTao.getNhomHocPhanTuChon();

        if (currentList == null) currentList = new LinkedList<>();
        currentList.addAll(newHocPhanTuChonList);

        chuongTrinhDaoTao.setNhomHocPhanTuChon(currentList);
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        // Chuyển đổi lại về ChuongTrinhDaoTaoRequest để trả về
        return modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
    }


    public void deleteHocPhanInCTDT(ChuongTrinhDaoTaoRequest request) {
        if (request == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        List<String> maHocPhanList = request.getHocPhanList().stream()
                .map(HocPhanDTO::getMaHp)
                .toList();
        chuongTrinhDaoTao.getHocPhanList().removeAll(
                chuongTrinhDaoTao.getHocPhanList().stream()
                        .filter(hocPhan -> maHocPhanList.contains(hocPhan.getMaHp()))
                        .toList()
        );
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
    }
    // Xoá học phần tự chọn trong học phần tự chọn hoặc xoá học phần trong học phần tự chọn
    @Transactional
    public void deleteHocPhanTuChonInCTDT(ChuongTrinhDaoTaoRequest request) {
        if (request == null || request.getKhoaHoc() == null || request.getMaNganh() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        List<Long> maNhomHocPhanTuChonList = request.getNhomHocPhanTuChon().stream()
                .map(HocPhanTuChonDTO::getId)
                .toList();
        maNhomHocPhanTuChonList.forEach(hocPhanTuChonService::deleteHocPhanTuChon);
        // Xoá học phần tự chọn trong chương trình đào tạo
        chuongTrinhDaoTao.setNhomHocPhanTuChon(
                chuongTrinhDaoTao.getNhomHocPhanTuChon().stream()
                        .filter(hocPhanTuChon -> !maNhomHocPhanTuChonList.contains(hocPhanTuChon.getId()))
                        .collect(Collectors.toList())
        );
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
    }
    @Transactional
    public void deleteHocPhanInHocPhanTuChon(ChuongTrinhDaoTaoRequest request) {
        if (request == null || request.getKhoaHoc() == null || request.getMaNganh() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        request.getNhomHocPhanTuChon().forEach( hptc ->{
            List<String> maHocPhanList = hptc.getHocPhanTuChonList().stream()
                    .map(HocPhanDTO::getMaHp)
                    .toList();
            hocPhanTuChonService.deleteHocPhanInHocPhanTuChon(hptc.getId(), maHocPhanList);
        });
    }


}

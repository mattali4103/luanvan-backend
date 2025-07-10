package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import com.luanvan.hocphanservice.entity.HocPhan;
import com.luanvan.hocphanservice.entity.HocPhanTuChon;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.Request.ChuongTrinhDaoTaoRequest;
import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.Request.CTDTDescriptionRequest;
import com.luanvan.hocphanservice.model.Request.KeHoachHocTapRequest;
import com.luanvan.hocphanservice.model.Response.ThongKeCTDT;
import com.luanvan.hocphanservice.model.Response.TinChiResponse;
import com.luanvan.hocphanservice.repository.ChuongTrinhDaoTaoRepository;
import com.luanvan.hocphanservice.repository.HocPhanRepository;
import com.luanvan.hocphanservice.repository.httpClient.NganhClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
                .orElseThrow(() -> new AppException("Không tìm thấy ngành trong csdl", ErrorCode.NOTFOUND));

        return modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
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

        if (hocPhanList.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }

        chuongTrinhDaoTao.setHocPhanList(hocPhanList);
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        return modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
    }


    public ChuongTrinhDaoTaoRequest update(ChuongTrinhDaoTaoRequest dto) {
        ModelMapper updateMapper = new ModelMapper();

        if (dto == null || dto.getMaNganh() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        updateMapper.getConfiguration().setSkipNullEnabled(true);
        updateMapper.typeMap(ChuongTrinhDaoTaoRequest.class, ChuongTrinhDaoTao.class)
                .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList));
        ChuongTrinhDaoTao chuongTrinhDaoTao = updateMapper.map(dto, ChuongTrinhDaoTao.class);
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        return updateMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoRequest.class);
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

        if(chuongTrinhDaoTaoRepository.existsChuongTrinhDaoTaoByKhoaHocAndMaNganh(request.getKhoaHoc(), request.getMaNganh())) {
            throw new AppException("Chương trình đào tạo đã tồn tại cho khóa học và ngành này", ErrorCode.INVALID_INPUT);
        }

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<String> maHocPhanList = new LinkedList<>();

            // Skip the header row if it exists (start from row 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                var row = sheet.getRow(i);
                if (row == null) continue;

                var cell = row.getCell(0);
                if (cell != null && !cell.getStringCellValue().trim().isEmpty()) {
                    String maHocPhan = cell.getStringCellValue().trim();
                    log.info("Reading course code: {}", maHocPhan);
                    maHocPhanList.add(maHocPhan);
                }
            }

            log.info("Total course codes found: {}", maHocPhanList.size());

            if (maHocPhanList.isEmpty()) {
                throw new AppException("No course codes found in the file", ErrorCode.INVALID_INPUT);
            }

            List<HocPhan> chuongTrinhList = hocPhanRepository.findByMaHpIn(maHocPhanList);
            log.info("Found {} courses in database out of {} requested", chuongTrinhList.size(), maHocPhanList.size());

            log.info("Course not found in database: {}",
                    maHocPhanList.stream()
                            .filter(maHocPhan -> chuongTrinhList.stream()
                                    .noneMatch(hocPhan -> hocPhan.getMaHp().equals(maHocPhan)))
                            .toList());

            if (chuongTrinhList.isEmpty()) {
                throw new AppException(ErrorCode.NOTFOUND);
            }

            modelMapper.typeMap(CTDTDescriptionRequest.class, ChuongTrinhDaoTao.class)
                    .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList));

            ChuongTrinhDaoTao chuongTrinhDaoTao = modelMapper.map(request, ChuongTrinhDaoTao.class);
            chuongTrinhDaoTao.setHocPhanList(chuongTrinhList);
            chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);

            log.info("Successfully created curriculum with {} courses", chuongTrinhList.size());
        } catch (IOException e) {
            log.error("Error reading Excel file: {}", e.getMessage());
            throw new AppException(ErrorCode.INVALID_INPUT);
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


    public List<HocPhanDTO> getNhomHocPhanTheChat() {
        ChuongTrinhDaoTao ctdt = chuongTrinhDaoTaoRepository.findById(4L)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        if(ctdt == null ){
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return ctdt.getHocPhanList().stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }
}

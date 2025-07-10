package com.luanvan.ketquahoctapservice.service;

import com.luanvan.ketquahoctapservice.Repository.KetQuaHocTapRepository;
import com.luanvan.ketquahoctapservice.Repository.httpClient.HocPhanClient;
import com.luanvan.ketquahoctapservice.entity.KetQuaHocTap;
import com.luanvan.ketquahoctapservice.exception.AppException;
import com.luanvan.ketquahoctapservice.exception.ErrorCode;
import com.luanvan.ketquahoctapservice.model.Response.*;
import com.luanvan.ketquahoctapservice.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KetQuaHocTapService {
    private final KetQuaHocTapRepository ketQuaHocTapRepository;
    private final ModelMapper modelMapper;
    private final HocPhanClient hocPhanClient;


    public CanhBaoHocVu isCanhBaoHocVu(String maSo){
        List<Long> maHocKyList = ketQuaHocTapRepository.findMaHocKyByMaSo(maSo);
        if (maHocKyList == null || maHocKyList.isEmpty()) {
            return new CanhBaoHocVu(); // Không có học kỳ nào, không cần cảnh báo
        }
        CanhBaoHocVu result = new CanhBaoHocVu();
        result.setMaSo(maSo);
        result.setLyDo("Bạn không có cảnh báo học vụ nào.");
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        // Lấy năm học duy nhất
        List<Long> maNamHocList = hocKyDTOList.stream()
                .map(hocKyDTO ->
                        hocKyDTO.getNamHoc() != null ? hocKyDTO.getNamHoc().getId() : null)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        List<NamHocDTO> namHocDTOList = hocPhanClient.getNamHocByMaNamHocIn(maNamHocList);
        //Nếu năm học đầu điểm dưới <= 1.2 thì cảnh báo
        //Nếu năm học thứ hai điểm dưới <= 1.4 thì cảnh báo
        // Nếu năm học thứ ba điểm dưới <= 1.6 thì cảnh báo
        // Các năm sau <= 1.8 thì cảnh báo
        if(namHocDTOList.size() == 1){
            namHocDTOList.get(0).getHocKyList().forEach(
                    hocKy -> {
                        Double diemTrungBinhTichLuy = getDiemTrungBinhTichLuy(maSo, hocKy.getMaHocKy());
                        if (diemTrungBinhTichLuy <= 1.2) {
                            result.setLyDo("Điểm trung bình tích lũy của bạn trong học kỳ " + hocKy.getTenHocKy() + " là " + diemTrungBinhTichLuy + ", dưới mức cảnh báo 1.2.");
                        }
                    }
            );
        }
        else if(namHocDTOList.size() == 2){
            namHocDTOList.get(1).getHocKyList().forEach(
                    hocKy -> {
                        Double diemTrungBinhTichLuy = getDiemTrungBinhTichLuy(maSo, hocKy.getMaHocKy());
                        if (diemTrungBinhTichLuy <= 1.4) {
                            result.setLyDo("Điểm trung bình tích lũy của bạn trong học kỳ " + hocKy.getTenHocKy() + " là " + diemTrungBinhTichLuy + ", dưới mức cảnh báo 1.4.");
                        }
                    }
            );
        }
        else if(namHocDTOList.size() == 3){
            namHocDTOList.get(2).getHocKyList().forEach(
                    hocKy -> {
                        Double diemTrungBinhTichLuy = getDiemTrungBinhTichLuy(maSo, hocKy.getMaHocKy());
                        if (diemTrungBinhTichLuy <= 1.6) {
                            result.setLyDo("Điểm trung bình tích lũy của bạn trong học kỳ " + hocKy.getTenHocKy() + " là " + diemTrungBinhTichLuy + ", dưới mức cảnh báo 1.6.");
                        }
                    }
            );
        }
        else{
            namHocDTOList.forEach(
                    namHoc -> namHoc.getHocKyList().forEach(
                            hocKy -> {
                                Double diemTrungBinhTichLuy = getDiemTrungBinhTichLuy(maSo, hocKy.getMaHocKy());
                                if (diemTrungBinhTichLuy <= 1.8) {
                                    result.setLyDo("Điểm trung bình tích lũy của bạn trong học kỳ " + hocKy.getTenHocKy() + " là " + diemTrungBinhTichLuy + ", dưới mức cảnh báo 1.8.");
                                }
                            }
                    )
            );
        }
        //Kiểm tra tín chỉ tích luỹ
        ThongKeKetQuaSinhVien thongKe = getThongKeByMaSo(maSo);
        long trungBinhSoTinChi = thongKe.getSoTinChiTichLuy() / namHocDTOList.size();
        if(trungBinhSoTinChi <= 30){
            result.setLyDo("Số tín chỉ tích lũy của bạn là " + thongKe.getSoTinChiTichLuy() + ", dưới mức cảnh báo 30 tín chỉ.");
        }

        return result;
    }

    public KetQuaHocTapByHocKy getKetQuaHocTapByHocKy(String maSo, Long maHocKy) {
        //Config laị modelMapper bỏ qua hocKy
        modelMapper.typeMap(KetQuaHocTap.class, KetQuaHocTapDetail.class)
                .addMappings(mapper -> mapper.skip(KetQuaHocTapDetail::setHocKy));


        // Kiểm tra mã số sinh viên và mã học kỳ không null và không rỗng
        if (maSo == null || maSo.isEmpty() || maHocKy == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Lấy danh sách kết quả học tập theo mã số sinh viên và mã học kỳ
        List<KetQuaHocTap> ketQuaHocTapList = ketQuaHocTapRepository.findByMaSoAndMaHocKy(maSo, maHocKy);
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            throw new AppException(ErrorCode.KET_QUA_HOC_TAP_EMPTY);
        }

        // Lấy danh sách mã học phần từ kết quả học tập
        List<String> maHocPhanList = ketQuaHocTapList.stream()
                .map(KetQuaHocTap::getMaHp)
                .distinct()
                .toList();

        // Lấy danh sách học phần từ mã học phần
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        if (hocPhanDTOList == null || hocPhanDTOList.isEmpty()) {
            return null;
        }

        // Lấy danh sách học kỳ từ mã học kỳ
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(Collections.singletonList(maHocKy));
        if (hocKyDTOList == null || hocKyDTOList.isEmpty()) {
            return null;
        }
        List<KetQuaHocTapDetail> kqhtDetailList = new ArrayList<>();
        ketQuaHocTapList.forEach(ketQuaHocTap -> {
            HocPhanDTO hocPhanDTO = hocPhanDTOList.stream()
                    .filter(hp -> hp.getMaHp().equals(ketQuaHocTap.getMaHp()))
                    .findFirst()
                    .orElse(null);
            if (hocPhanDTO != null) {
                KetQuaHocTapDetail detail = modelMapper.map(ketQuaHocTap, KetQuaHocTapDetail.class);
                detail.setHocPhan(hocPhanDTO);
                detail.setSoTinChi((long) hocPhanDTO.getTinChi());
                kqhtDetailList.add(detail);
            }
        });
        Double diemTrungBinhHocKy = calculateDiemTrungBinh(ketQuaHocTapList);
        Double diemTrungBinhTichLuy = getDiemTrungBinhTichLuy(maSo, maHocKy);
        KetQuaHocTapByHocKy result = new KetQuaHocTapByHocKy();
        result.setHocKy(hocKyDTOList.stream()
                .filter(hk -> hk.getMaHocKy().equals(maHocKy))
                .findFirst()
                .orElse(null));
        result.setKetQuaHocTapList(kqhtDetailList);
        result.setDiemTrungBinhHocKy(diemTrungBinhHocKy);
        result.setDiemTrungBinhTichLuy(diemTrungBinhTichLuy);
        return result;
    }
    public List<KetQuaHocTapDTO> getKetQuaHocTapCaiThien(String maSo){
        // Kiểm tra mã số sinh viên không null và không rỗng
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        // Lấy ra danh sách kết quả học tập có mã số bị trùng
        List<KetQuaHocTap> ketQuaHocTapList = ketQuaHocTapRepository.findDuplicateKetQuaHocTapByMaSo(maSo);
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            return Collections.emptyList();
        }
        return ketQuaHocTapList.stream().map(k -> modelMapper.map(k, KetQuaHocTapDTO.class)).toList();
    }

    public ThongKeKetQuaSinhVien getThongKeByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        // Lấy danh sách kết quả học tập theo mã số sinh viên
        List<KetQuaHocTap> ketQuaHocTapList = ketQuaHocTapRepository.findByMaSo(maSo);
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            return new ThongKeKetQuaSinhVien();
        }

        //Lấy mã học kỳ gần nhất từ kết quả học tập
        Long maHocKy = ketQuaHocTapList.stream()
                .map(KetQuaHocTap::getMaHocKy)
                .max(Long::compareTo)
                .orElse(null);



        //Tính tổng số tín chỉ tích lũy
        Long soTinChiTichLuy = ketQuaHocTapList.stream()
                .filter(k -> k.getDiemSo() != null)
                .mapToLong(KetQuaHocTap::getSoTinChi)
                .sum();

        //Lấy tổng số tín chỉ cải thiện
        long soTinChicaiThien;
        List<KetQuaHocTapDTO> kqhtCaiThien = getKetQuaHocTapCaiThien(maSo);
        soTinChicaiThien = kqhtCaiThien.stream()
                .mapToLong(KetQuaHocTapDTO::getSoTinChi)
                .sum();

        double diemTrungBinhTichLuy = getDiemTrungBinhTichLuy(maSo, maHocKy);
        String xepLoai = xepLoaiSinhVien(diemTrungBinhTichLuy);


        return ThongKeKetQuaSinhVien.builder()
                .maSo(maSo)
                .xepLoai(xepLoai)
                .soTinChiTichLuy(soTinChiTichLuy)
                .soTinChiCaiThien(soTinChicaiThien)
                .diemTBTichLuy(diemTrungBinhTichLuy)
                .build();
    }

    //Tìm mã học kỳ theo mã số sinh viên (No pagination - legacy method)
    public List<HocKyDTO> getMaHocKyByMaSo(String maSo) {
        List<Long> maHocKyList = ketQuaHocTapRepository.findMaHocKyByMaSo(maSo);
        if (maHocKyList == null || maHocKyList.isEmpty()) {
            return Collections.emptyList();
        }
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        return hocKyDTOList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<KetQuaHocTapDetail> getHocPhanFailed(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        // Lấy danh sách kết quả học tập theo mã số sinh viên và điểm F
        List<KetQuaHocTap> ketQuaHocTapList = ketQuaHocTapRepository.findKetQuaHocTapsByDiemChuEqualsIgnoreCase(maSo);
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> maHocPhanList = ketQuaHocTapList.stream()
                .map(KetQuaHocTap::getMaHp)
                .distinct()
                .toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        return getKetQuaHocTapDetailList(
                ketQuaHocTapList.stream().map(k -> modelMapper.map(k, KetQuaHocTapDTO.class)).toList(),
                hocPhanDTOList,
                Collections.emptyList() // HocKyDTO không cần thiết trong trường hợp này
        );
    }

    public List<KetQuaHocTapDetail> getHocPhanCaiThienByMaSo(String maSo) {
        // Kiểm tra mã số sinh viên không null và không rỗng
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Lấy danh sách kết quả học tập theo mã số sinh viên và điểm số nhỏ hơn 7
        List<KetQuaHocTap> ketQuaHocTapList = ketQuaHocTapRepository.findMaHpByMaSoAndDiemSoLessThan7(maSo);
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> maHocPhanList = ketQuaHocTapList.stream()
                .map(KetQuaHocTap::getMaHp)
                .distinct()
                .toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        return getKetQuaHocTapDetailList(
                ketQuaHocTapList.stream().map(k -> modelMapper.map(k, KetQuaHocTapDTO.class)).toList(),
                hocPhanDTOList,
                Collections.emptyList() // HocKyDTO không cần thiết trong trường hợp này
        );
    }




    // Lấy điểm trung bình theo mã số sinh viên
    public List<DiemTrungBinh> getDiemTrungBinhListByMaSo(String maSo) {
        List<KetQuaHocTapDTO> ketQuaHocTap = ketQuaHocTapRepository.findByMaSo(maSo)
                .stream().map(k -> modelMapper.map(k, KetQuaHocTapDTO.class))
                .toList();

        if (ketQuaHocTap.isEmpty()) {
            return Collections.emptyList();
        }
        List<HocKyDTO> hocKyDTOList = getHocKyFromKQHT(ketQuaHocTap);
        List<DiemTrungBinh> diemTrungBinhList = new ArrayList<>();
        hocKyDTOList.forEach(hocKyDTO -> {
            DiemTrungBinh dtb = new DiemTrungBinh();
            dtb.setHocKy(hocKyDTO);
            dtb.setDiemTrungBinh(getDiemTrungBinhHocKy(maSo, hocKyDTO.getMaHocKy()));
            dtb.setDiemTrungBinhTichLuy(getDiemTrungBinhTichLuy(maSo, hocKyDTO.getMaHocKy()));
            diemTrungBinhList.add(dtb);
        });
        return diemTrungBinhList;
    }

    // Get Diem Trung Binh Tich Luy by maSo and maHocKy
    public Double getDiemTrungBinhTichLuy(String maSo, Long maHocKy) {
        List<KetQuaHocTap> kqht = ketQuaHocTapRepository.findByMaSoAndMaHocKyIsLessThanEqual(maSo, maHocKy);
        kqht = (kqht == null) ? Collections.emptyList() :
                kqht.stream()
                        .filter(ketQua -> ketQua != null && !ketQua.isDieuKien())
                        .toList();
        return calculateDiemTrungBinh(kqht);
    }

    // Get Diem Trung Binh HocKy by maSo and maHocKy
    public Double getDiemTrungBinhHocKy(String maSo, Long maHocKy) {
        List<KetQuaHocTap> kqhtList = ketQuaHocTapRepository.findByMaSoAndMaHocKy(maSo, maHocKy);
        if (kqhtList == null || kqhtList.isEmpty()) {
            throw new AppException(ErrorCode.KET_QUA_HOC_TAP_EMPTY);
        }
        return calculateDiemTrungBinh(kqhtList);
    }

    // Get all KetQuaHocTapDetail by maSo
    public PageResponse<KetQuaHocTapDetail> getKetQuaHocTapDetailList(String maSo, int page, int size) {
        // Kiểm tra mã số sinh viên không null và không rỗng
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Kiểm tra page và size hợp lệ
        if (page < 1 || size <= 0) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Tạo Pageable với page được điều chỉnh (page-1 vì Spring Page sử dụng zero-based index)
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "maHocKy"));

        // Lấy danh sách kết quả học tập theo mã số sinh viên với pagination
        Page<KetQuaHocTap> kqhtPage = ketQuaHocTapRepository.findByMaSo(maSo, pageable);

        if (kqhtPage.isEmpty()) {
            return PageResponse.<KetQuaHocTapDetail>builder()
                    .build();
        }

        List<KetQuaHocTap> kqhtList = kqhtPage.getContent();

        // Prepare lists of unique keys to fetch related data
        List<String> maHocPhanList = kqhtList.stream()
                .map(KetQuaHocTap::getMaHp)
                .distinct()
                .toList();

        List<Long> maHocKyList = kqhtList.stream()
                .map(KetQuaHocTap::getMaHocKy)
                .distinct()
                .toList();

        // Fetch related data
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        if (hocPhanDTOList == null || hocPhanDTOList.isEmpty()) {
            return PageResponse.<KetQuaHocTapDetail>builder()
                    .totalElements(0)
                    .totalPages(0)
                    .currentPage(page)
                    .data(Collections.emptyList())
                    .build();
        }

        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        if (hocKyDTOList == null || hocKyDTOList.isEmpty()) {
            return PageResponse.<KetQuaHocTapDetail>builder()
                    .totalElements(0)
                    .totalPages(0)
                    .currentPage(page)
                    .data(Collections.emptyList())
                    .build();
        }

        // Convert KetQuaHocTap to KetQuaHocTapDetail with related HocPhan and HocKy
        List<KetQuaHocTapDetail> kqhtDetailList = getKetQuaHocTapDetailList(
                kqhtList.stream().map(k -> modelMapper.map(k, KetQuaHocTapDTO.class)).toList(),
                hocPhanDTOList,
                hocKyDTOList
        );


        // Build and return PageResponse with pagination information from the original Page object
        return PageResponse.<KetQuaHocTapDetail>builder()
                .totalElements(kqhtPage.getTotalElements())
                .totalPages(kqhtPage.getTotalPages())
                .currentPage(page)
                .data(kqhtDetailList)
                .build();
    }

    @SuppressWarnings("unused")
    public List<KetQuaHocTapDTO> creates(List<KetQuaHocTapDTO> ketQuaHocTapDTOList) {
        if (ketQuaHocTapDTOList == null || ketQuaHocTapDTOList.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<KetQuaHocTap> ketQuaHocTapList = new ArrayList<>();
        for (KetQuaHocTapDTO dto : ketQuaHocTapDTOList) {
            KetQuaHocTap ketQuaHocTap = modelMapper.map(dto, KetQuaHocTap.class);
            ketQuaHocTapList.add(ketQuaHocTap);
        }
        List<KetQuaHocTap> savedKetQuaHocTaps = ketQuaHocTapRepository.saveAll(ketQuaHocTapList);
        List<KetQuaHocTapDTO> result = new ArrayList<>();
        for (KetQuaHocTap saved : savedKetQuaHocTaps) {
            result.add(modelMapper.map(saved, KetQuaHocTapDTO.class));
        }
        return result;
    }

    @SuppressWarnings("unused")
    public KetQuaHocTapDTO create(KetQuaHocTapDTO ketQuaHocTapDTO) {
        if (ketQuaHocTapDTO == null) {
            throw new AppException(ErrorCode.KET_QUA_HOC_TAP_EMPTY);
        }
        KetQuaHocTap ketQuaHocTap = modelMapper.map(ketQuaHocTapDTO, KetQuaHocTap.class);
        KetQuaHocTap savedKetQuaHocTap = ketQuaHocTapRepository.save(ketQuaHocTap);
        return modelMapper.map(savedKetQuaHocTap, KetQuaHocTapDTO.class);
    }

    //    Read from formated file excel
    public void createKHHTByFileExcel(MultipartFile file) {
        List<KetQuaHocTap> DSKhht = new LinkedList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            sheet.forEach(row -> {
                if (row.getRowNum() == 0) return; // Skip header row
                KetQuaHocTap ketQuaHocTap = new KetQuaHocTap();
                ketQuaHocTap.setDiemChu(getStringCellValue(row.getCell(0)));
                ketQuaHocTap.setDiemSo(row.getCell(1).getNumericCellValue());
                ketQuaHocTap.setDieuKien(getBooleanCellValue(row.getCell(2)));
                ketQuaHocTap.setMaNhomHP((long) row.getCell(3).getNumericCellValue());
                ketQuaHocTap.setMaSo(getStringCellValue(row.getCell(4)));
                ketQuaHocTap.setMaHocKy((long) row.getCell(5).getNumericCellValue());
                ketQuaHocTap.setMaHp(getStringCellValue(row.getCell(6)));
                ketQuaHocTap.setSoTinChi((long) row.getCell(7).getNumericCellValue());
                DSKhht.add(ketQuaHocTap);
            });
            ketQuaHocTapRepository.saveAll(DSKhht);
        } catch (IOException e) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
    }

    private List<KetQuaHocTapDetail> getKetQuaHocTapDetailList(List<KetQuaHocTapDTO> kqhtList, List<HocPhanDTO> hocPhanDTOList, List<HocKyDTO> hocKyDTOList) {
        Map<String, HocPhanDTO> hocPhanMap = hocPhanDTOList.stream()
                .collect(Collectors.toMap(HocPhanDTO::getMaHp, Function.identity()));
        Map<Long, HocKyDTO> hocKyMap = hocKyDTOList.stream()
                .collect(Collectors.toMap(HocKyDTO::getMaHocKy, Function.identity(), (k1, k2) -> k1));
        List<KetQuaHocTapDetail> kqhtDetailList = new ArrayList<>();
        kqhtList.forEach(ketQuaHocTap -> {
            HocPhanDTO hocPhanDTO = hocPhanMap.get(ketQuaHocTap.getMaHp());
            if (hocPhanDTO != null) {
                KetQuaHocTapDetail detail = modelMapper.map(ketQuaHocTap, KetQuaHocTapDetail.class);
                // Kiểm tra và áp dụng hocKy chỉ khi hocKyMap không rỗng
                if (!hocKyMap.isEmpty()) {
                    HocKyDTO hocKyDTO = hocKyMap.get(ketQuaHocTap.getMaHocKy());
                    if (hocKyDTO != null) {
                        detail.setHocKy(hocKyDTO);
                    }
                }
                detail.setHocPhan(hocPhanDTO);
                detail.setSoTinChi((long) hocPhanDTO.getTinChi());
                kqhtDetailList.add(detail);
            }
        });
        return kqhtDetailList;
    }

    private List<HocKyDTO> getHocKyFromKQHT(List<KetQuaHocTapDTO> KqhtList) {

        List<Long> maHocKyList = KqhtList.stream()
                .map(KetQuaHocTapDTO::getMaHocKy)
                .distinct()
                .toList();
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        if (hocKyDTOList.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return hocKyDTOList;
    }

    private String xepLoaiSinhVien(double diemTrungBinhTichLuy){
        if (diemTrungBinhTichLuy >= 3.6) {
            return "Xuất sắc";
        } else if (diemTrungBinhTichLuy >= 3.2) {
            return "Giỏi";
        } else if (diemTrungBinhTichLuy >= 2.5) {
            return "Khá";
        } else if (diemTrungBinhTichLuy >= 2.0) {
            return "Trung bình";
        } else if (diemTrungBinhTichLuy >= 1.0) {
            return "Yếu";
        } else {
            return "Kém";
        }
    }

    private boolean getBooleanCellValue(Cell cell) {
        if (cell == null) return false;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().equalsIgnoreCase("true");
            case NUMERIC -> cell.getNumericCellValue() > 0;
            default -> false;
        };
    }

    private Double calculateDiemTrungBinh(List<KetQuaHocTap> ketQuaHocTapList) {
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            return null;
        }
        double diemTB = 0.0;
        long tongSoTinChi = 0;
        for (KetQuaHocTap ketQua : ketQuaHocTapList) {
            if (ketQua != null && ketQua.getDiem4() != null && ketQua.getSoTinChi() != null) {
                if (ketQua.getDiemChu().equals("I") || ketQua.getDiemChu().equals("F")) {
                    continue;
                }
                diemTB += ketQua.getDiem4() * ketQua.getSoTinChi();
                tongSoTinChi += ketQua.getSoTinChi();
            }
        }
        // Nếu tổng số tín chỉ là 0, trả về null
        if (tongSoTinChi == 0) {
            return null;
        }
        double result = (diemTB / tongSoTinChi);
        // Trả về điểm trung bình nếu tổng số tín chỉ lớn hơn 0, ngược lại trả về null
        return diemTB > 0 ? LamTronDiem(result) : null;
    }

    private double LamTronDiem(double diem) {
        if (diem < 0) {
            return 0.0;
        } else if (diem > 4) {
            return 4.0;
        } else {
            return Math.round(diem * 100.0) / 100.0; //
        }
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}

package com.luanvan.kehoachhoctapservice.service;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTap;
import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTapMau;
import com.luanvan.kehoachhoctapservice.exception.AppException;
import com.luanvan.kehoachhoctapservice.exception.ErrorCode;

import com.luanvan.kehoachhoctapservice.model.dto.*;
import com.luanvan.kehoachhoctapservice.model.request.AddKHHTRequest;
import com.luanvan.kehoachhoctapservice.model.request.HocPhanRequest;
import com.luanvan.kehoachhoctapservice.model.request.KeHoachHocTapRequest;
import com.luanvan.kehoachhoctapservice.model.response.*;
import com.luanvan.kehoachhoctapservice.repository.KeHoachHocTapMauRepository;
import com.luanvan.kehoachhoctapservice.repository.KeHoachHocTapRepository;
import com.luanvan.kehoachhoctapservice.repository.httpClient.HocPhanClient;
import com.luanvan.kehoachhoctapservice.repository.httpClient.KQHTClient;
import com.luanvan.kehoachhoctapservice.repository.httpClient.ProfileClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeHoachHocTapService {
    private final ModelMapper modelMapper;
    private final KeHoachHocTapRepository keHoachHocTapRepository;
    private final KeHoachHocTapMauRepository keHoachHocTapMauRepository;
    private final HocPhanClient hocPhanClient;
    private final ProfileClient profileClient;
    private final KQHTClient kQHTClient;


    public List<HocKyDTO> geHocKyByMaSo(String maSo) {
        List<Long> hocKyList = keHoachHocTapRepository.findMaHocKyByMaSo(maSo);
        if (hocKyList.isEmpty()) {
            return Collections.emptyList();
        }
        return hocPhanClient.getHocKyIn(hocKyList);
    }

    //Số tín chỉ đăng ký gần nhất của sinh viên theo mã số
    public Long getCountTinChiDangKyByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        // Lấy kế hoạch học tập gần nhất của sinh viên theo mã số
        List<KeHoachHocTap> khhtNearestHocKy = keHoachHocTapRepository.findLatestKeHoachHocTapByMaSo(maSo);


        if( khhtNearestHocKy.isEmpty()) {
            return 0L;
        }
        List<String> maHocPhanList = khhtNearestHocKy.stream()
                .map(KeHoachHocTap::getMaHocPhan)
                .toList();
        return hocPhanClient.countTinChiIn(maHocPhanList);
    }

    public TinChiResponse countKeHoachHocTapsByMaSo(String maSo, String khoaHoc, Long maNganh) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        if (khoaHoc == null || khoaHoc.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<KeHoachHocTap> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);
        if (khhtList == null || khhtList.isEmpty()) {
            return new TinChiResponse(0L, 0L, 0L, 0L);
        }

        modelMapper.typeMap(KeHoachHocTap.class, KeHoachHocTapRequest.class)
                .addMappings(mapper -> mapper.map(KeHoachHocTap::getMaHocPhan, KeHoachHocTapRequest::setMaHocPhan));
        List<KeHoachHocTapRequest> request = khhtList.stream().map(khht -> modelMapper.map(khht, KeHoachHocTapRequest.class)).toList();
        return hocPhanClient.getCountTinChiByCTDT(khoaHoc, maNganh, request);
    }

    public List<ThongKeTinChi> countKHHTGroupByHocKy(String maSo) {
        // Kiểm tra tham số đầu vào
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        // Lấy danh sách kế hoạch học tập của sinh viên từ database
        List<KeHoachHocTap> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);
        if (khhtList == null || khhtList.isEmpty()) {
            return Collections.emptyList();
        }

        // Nhóm kế hoạch học tập theo học kỳ
        Map<Long, List<KeHoachHocTap>> nhomTheoHocKy = khhtList.stream()
                .collect(Collectors.groupingBy(KeHoachHocTap::getMaHocKy));

        // Chuẩn bị danh sách mã học phần và mã học kỳ để gọi API batch
        List<String> maHocPhanList = khhtList.stream().map(KeHoachHocTap::getMaHocPhan).distinct().collect(Collectors.toList());
        List<Long> maHocKyList = new ArrayList<>(nhomTheoHocKy.keySet());

        // Gọi service khác để lấy thông tin học kỳ và học phần
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);

        // Tạo Map để tra cứu nhanh thông tin học phần và học kỳ
        Map<String, HocPhanDTO> mapHocPhan = hocPhanDTOList.stream()
                .collect(Collectors.toMap(HocPhanDTO::getMaHp, hp -> hp));

        Map<Long, HocKyDTO> mapHocKy = hocKyDTOList.stream()
                .collect(Collectors.toMap(HocKyDTO::getMaHocKy, hk -> hk));

        // Tính toán thống kê tín chỉ cho từng học kỳ
        List<ThongKeTinChi> result = new ArrayList<>();
        for (Map.Entry<Long, List<KeHoachHocTap>> entry : nhomTheoHocKy.entrySet()) {
            Long maHocKy = entry.getKey();
            List<KeHoachHocTap> keHoachTrongHocKy = entry.getValue();
            // Lấy thông tin học kỳ
            HocKyDTO hocKy = mapHocKy.get(maHocKy);
            if (hocKy == null) {
                log.warn("Không tìm thấy thông tin học kỳ có mã: {}", maHocKy);
                continue;
            }
            // Khởi tạo biến đếm tín chỉ cho học kỳ hiện tại
            long tinChiDangKyHocKyHienTai = 0;
            long tinChiCaiThienHocKyHienTai = 0;
            // Duyệt qua từng kế hoạch học tập trong học kỳ để tính tín chỉ
            for (KeHoachHocTap keHoach : keHoachTrongHocKy) {
                HocPhanDTO hocPhan = mapHocPhan.get(keHoach.getMaHocPhan());
                if (hocPhan != null) {
                    // Phân loại tín chỉ theo loại học phần
                    if (keHoach.isHocPhanCaiThien()) {
                        tinChiCaiThienHocKyHienTai += hocPhan.getTinChi();
                    } else {
                        tinChiDangKyHocKyHienTai += hocPhan.getTinChi();
                    }
                } else {
                    log.warn("Không tìm thấy thông tin học phần có mã: {}", keHoach.getMaHocPhan());
                }
            }

            // Tạo object thống kê cho học kỳ
            ThongKeTinChi thongKe = new ThongKeTinChi();
            thongKe.setHocKy(hocKy);
            thongKe.setSoTinChiDangKy(tinChiDangKyHocKyHienTai);
            thongKe.setSoTinChiCaiThien(tinChiCaiThienHocKyHienTai);
            result.add(thongKe);
        }

        // Sắp xếp kết quả theo năm học và tên học kỳ
        result.sort((a, b) -> {
            if (!a.getHocKy().getNamHoc().getNamBatDau().equals(b.getHocKy().getNamHoc().getNamBatDau())) {
                return a.getHocKy().getNamHoc().getNamBatDau().compareTo(b.getHocKy().getNamHoc().getNamBatDau());
            }
            return a.getHocKy().getTenHocKy().compareTo(b.getHocKy().getTenHocKy());
        });
        return result;
    }


    public List<HocPhanDTO> getHocPhanNotInCTDT(String maSo, String khoaHoc, Long maNganh) {
        List<String> maHpInKHHT = keHoachHocTapRepository.findMaHocPhanByMaSo(maSo);
        return hocPhanClient.getHocPhanNotInCTDT(khoaHoc, maNganh, maHpInKHHT);
    }

    public List<KeHoachHocTapDetail> getKHHTDetailByMaSoAndHocKy(String maSo, Long maHocKy) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        if (maHocKy == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<KeHoachHocTapDTO> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSoAndMaHocKy(maSo, maHocKy)
                .stream().map(khht -> modelMapper.map(khht, KeHoachHocTapDTO.class)).toList();
        if (khhtList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> maHocPhanList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocPhan)
                .toList();
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(Collections.singletonList(maHocKy));
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);

        return getKeHoachHocTapDetails(khhtList, hocPhanDTOList, hocKyDTOList);
    }

    //    Lấy học phần có trong khht theo mã số sinh viên có phân trang
    public PageResponse<KeHoachHocTapDetail> getKHHTDetailByMaSo(String maSo, int page, int size) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "maHocKy", "maHocPhan");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<KeHoachHocTap> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo, pageable);
        if (khhtList.isEmpty()) {
            return PageResponse.<KeHoachHocTapDetail>builder()
                    .currentPage(page)
                    .pageSize(size)
                    .totalElements(0)
                    .totalPages(0)
                    .data(Collections.emptyList())
                    .build();
        }
        List<KeHoachHocTapDTO> khhtDTOList = khhtList.stream()
                .map(khht -> modelMapper.map(khht, KeHoachHocTapDTO.class))
                .toList();

        List<HocKyDTO> hocKyDTOList = getHocKyFromKHHTList(khhtDTOList);
        List<HocPhanDTO> hocPhanDTOList = getHocPhanFromKHHTList(khhtDTOList);

        List<KeHoachHocTapDetail> khhtDetailList = getKeHoachHocTapDetails(khhtDTOList, hocPhanDTOList, hocKyDTOList);

        return PageResponse.<KeHoachHocTapDetail>builder()
                .currentPage(page)
                .pageSize(size)
                .totalElements(khhtList.getTotalElements())
                .totalPages(khhtList.getTotalPages())
                .data(khhtDetailList)
                .build();
    }


    //    Lấy học phần có trong khht
    public List<KeHoachHocTapDetail> getKHHTDetailByLoaiHP(String maSo, String khoaHoc, Long maNganh, String loaiHp) {
        List<KeHoachHocTapDTO> khhtList = getKeHoachHocTapsByMaSo(maSo);
        if (khhtList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> maHocKyList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocKy)
                .toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanInCTDTByLoaiHp(HocPhanRequest.builder().khoaHoc(khoaHoc).maNganh(maNganh).loaiHp(loaiHp).build());
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);

        return getKeHoachHocTapDetails(khhtList, hocPhanDTOList, hocKyDTOList);
    }

    public List<KeHoachHocTapDetail> getKHHTDetail(String maSo) {
        List<KeHoachHocTapDTO> khhtList = getKeHoachHocTapsByMaSo(maSo);
        if (khhtList.isEmpty()) {
            return Collections.emptyList();
        }
        List<HocPhanDTO> hocPhanDTOList = getHocPhanFromKHHTList(khhtList);
        List<HocKyDTO> hocKyDTOList = getHocKyFromKHHTList(khhtList);
        return getKeHoachHocTapDetails(khhtList, hocPhanDTOList, hocKyDTOList);
    }

    public List<KeHoachHocTapDTO> getKeHoachHocTapsByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        List<KeHoachHocTap> keHoachHocTaps = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);
        if (keHoachHocTaps.isEmpty()) {
            return Collections.emptyList();
        }
        return keHoachHocTaps.stream()
                .map(khht -> modelMapper.map(khht, KeHoachHocTapDTO.class))
                .toList();
    }

    public List<String> getMaHocPhanByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }

        List<String> maHocPhanList = keHoachHocTapRepository.findMaHocPhanByMaSo(maSo);

        if (maHocPhanList.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return maHocPhanList;
    }


    public KeHoachHocTapDTO update(KeHoachHocTapDTO khht) {
        if (khht.getId() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        KeHoachHocTap existingKHHT = keHoachHocTapRepository.findById(khht.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        modelMapper.map(khht, existingKHHT);
        keHoachHocTapRepository.save(existingKHHT);

        return modelMapper.map(existingKHHT, KeHoachHocTapDTO.class);
    }


    @Transactional
    public KeHoachHocTapDTO create(AddKHHTRequest request) {
        if (request == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        //Kiểm tra học phần này con tồn tại trong hệ thống không
        if (isHocPhanExist(request.getMaHocPhan())) {
            throw new AppException(ErrorCode.HOCPHAN_NOTFOUND);
        }
//      Kiểm tra học phần đang nhập có phải là học phần cải thiện không
        if (request.isHocPhanCaiThien() && isKeHoachHocTapExist(request.getMaSo(), request.getMaHocPhan())) {
            throw new AppException(ErrorCode.KHHT_NOTFOUND);
        }

        KeHoachHocTap khht = modelMapper.map(request, KeHoachHocTap.class);

        keHoachHocTapRepository.save(khht);
        return modelMapper.map(khht, KeHoachHocTapDTO.class);
    }

    public void creates(List<KeHoachHocTapRequest> request) {
        if (request == null || request.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<KeHoachHocTap> khht = request.stream().map(
                khhtRequest -> modelMapper.map(khhtRequest, KeHoachHocTap.class)).toList();
        // Kiểm tra từng học phần trong kế hoạch học tập
        for (KeHoachHocTap khhtItem : khht) {
            // Kiểm tra học phần có tồn tại trong hệ thống không
            if (!isHocPhanExist(khhtItem.getMaHocPhan())) {
                throw new AppException(ErrorCode.HOCPHAN_NOTFOUND);
            }
        }
        //Kiểm tra học phần tuyên quyết
        List<String> maHpInKhht = khht.stream()
                .map(KeHoachHocTap::getMaHocPhan)
                .distinct()
                .toList();
        List<String> maHpInRequest = request.stream()
                .map(KeHoachHocTapRequest::getMaHocPhan)
                .distinct()
                .toList();

        checkHocPhanTuyenQuyet(maHpInKhht, maHpInRequest);

        // Lưu tất cả kế hoạch học tập vào cơ sở dữ liệu
        keHoachHocTapRepository.saveAll(khht);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        KeHoachHocTap existingKHHT = keHoachHocTapRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        List<KeHoachHocTap> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(existingKHHT.getMaSo());
        List<String> kqhtList = kQHTClient.getHocPhanByMaSo(existingKHHT.getMaSo());
        // Kiểm tra nếu sinh viên đã đăng ký học phần này trong kết quả học tập thì không được xóa
        if (khhtList.stream().anyMatch(khht -> khht.getMaHocPhan().equals(existingKHHT.getMaHocPhan())) ||
                kqhtList.contains(existingKHHT.getMaHocPhan())) {
            throw new AppException(ErrorCode.CUSTOM_MESSAGE, "Bạn không thể xóa học phần đã học");
        }
        // Kiểm tra xem học phần này có phải là điều kiện tiên quyết của học phần khác không
        List<String> maHocPhanList = khhtList.stream()
                .map(KeHoachHocTap::getMaHocPhan)
                .distinct()
                .toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        List<String> hocPhanTuyenQuyetList = hocPhanDTOList.stream()
                .map(HocPhanDTO::getHocPhanTienQuyet)
                .filter(Objects::nonNull)
                .flatMap(hp -> Arrays.stream(hp.split(",")))
                .toList();
        log.info("Danh sách hc phần tuyên quyết: {}", hocPhanTuyenQuyetList);
        // Kiểm tra xem học phần này có phải là học phần tuyên quyết của học phần khác không
        if (hocPhanTuyenQuyetList.contains(existingKHHT.getMaHocPhan())) {
            throw new AppException(ErrorCode.CUSTOM_MESSAGE, "Không thể xoá học phần vì học phần " + existingKHHT.getMaHocPhan() + " là học phần tuyên quyết của học phần khác.");
        }
        keHoachHocTapRepository.delete(existingKHHT);
    }

    public List<HocPhanDTO> getRecommendKHHT(String maSo, String khoaHoc, Long maNganh){
//        HocKyDTO hocKyHienTai = hocPhanClient.getHocKyHienTai();
//        log.info("Học kỳ hiện tại: {}", hocKyHienTai.getMaHocKy());
        // Tìm kế hoạch học tập mẫu theo học kỳ gần nhất (bắt đầu từ học kỳ tiếp theo)
//        Long hocKyTimKiem = hocKyHienTai.getMaHocKy() + 1;
        // Kiểm tra sinh viên đã học đên học kỳ nào
//        Long hocKyGanNhat = keHoachHocTapRepository.findLatestMaHocKyByMaSo(maSo).orElse(0L);
        List<KeHoachHocTap> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);
        if( khhtList.isEmpty()) {
            log.info("Sinh viên {} chưa có kế hoạch học tập nào", maSo);
            return Collections.emptyList();
        }

        // Lấy danh sách học phần đã đăn ký của sinh viên
        List<String> maHocPhanDaDangKyList = khhtList.stream()
                .map(KeHoachHocTap::getMaHocPhan)
                .distinct() // Loại bỏ trùng lặp
                .toList();
        // Danh sách học phàn đã học của sinh viên
        List<String> maHocPhanDaHocList = kQHTClient.getHocPhanByMaSo(maSo);
        maHocPhanDaHocList.forEach(hp ->{
            log.info("Học phần đã học của sinh viên {}: {}", maSo, hp);
        });
        if (maHocPhanDaHocList.isEmpty()) {
            log.info("Sinh viên {} chưa học học phần nào", maSo);
            return Collections.emptyList();
        }
        List<KeHoachHocTapMau> keHoachHocTapMau = keHoachHocTapMauRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh);
        if (keHoachHocTapMau.isEmpty()) {
            log.info("Không tìm thấy kế hoạch học tập mẫu cho khóa {} ngành {}", khoaHoc, maNganh);
            return Collections.emptyList();
        }
        // Lọc học phần sinh viên chưa đăng ký và chưa học
        Set<String> filteredMaHocPhan = new HashSet<>(maHocPhanDaDangKyList);
        filteredMaHocPhan.addAll(maHocPhanDaHocList);
        log.info("Đã lọc {} học phần đã đăng ký và đã học của sinh viên {}", filteredMaHocPhan.size(), maSo);
//      Lấy danh sách học phần đã lọc
        List<HocPhanDTO> hocPhanDaLoc = hocPhanClient.getHocPhanIn(new ArrayList<>(filteredMaHocPhan));

//        // Sắp xếp lại dựa trên kế hoạch học tập mẫu keHoachHocTapMau
//        List<KeHoachHocTapMau> sortedKeHoachHocTapMau = keHoachHocTapMau.stream()
//                .filter(khhtMau -> hocPhanDaLoc.stream()
//                        .anyMatch(hp -> hp.getMaHp().equals(khhtMau.getMaHocPhan())))
//                .sorted(Comparator.comparing(KeHoachHocTapMau::getMaHocKy))
//                .toList();

        // Lọc KHHT mẫu để chỉ giữ những học phần sinh viên CHƯA đăng ký và CHƯA học
        List<KeHoachHocTapMau> sortedKeHoachHocTapMau = keHoachHocTapMau.stream()
                .filter(khhtMau -> !filteredMaHocPhan.contains(khhtMau.getMaHocPhan()))
                .sorted(Comparator.comparing(KeHoachHocTapMau::getMaHocKy))
                .toList();


        // Lấy học kỳ gần nhất từ kế hoạch học tập của sinh viên
        Long hocKyGanNhat = khhtList.stream()
                .map(KeHoachHocTap::getMaHocKy)
                .max(Long::compareTo)
                .orElse(0L);
        log.info("Học kỳ gần nhất của sinh viên {} là: {}", maSo, hocKyGanNhat);

        // Lọc kế hoạch học tập mẫu theo 2 học kỳ gần nhất
        List<KeHoachHocTapMau> khhtGoiY = sortedKeHoachHocTapMau.stream()
                .filter(khhtMau -> khhtMau.getMaHocKy() >= hocKyGanNhat && khhtMau.getMaHocKy() <= hocKyGanNhat + 2)
                .toList();

        // Lấy kế hoạch học tập mẫu từ học kỳ gần nhất trở đi

        // Lấy danh sách học phần đã đăng ký của sinh viên

        // Lấy mã học phần gợi ý từ kế hoạch học tập mẫu
        List<String> maHocPhanGoiY = khhtGoiY.stream()
                .map(KeHoachHocTapMau::getMaHocPhan)
                .collect(Collectors.toList());

        if (maHocPhanGoiY.isEmpty()) {
            log.info("Không có học phần nào để gợi ý cho sinh viên {}", maSo);
            return Collections.emptyList();
        }

        log.info("Tìm thấy {} học phần gợi ý cho sinh viên {} từ học kỳ {}: {}",
                maHocPhanGoiY.size(), maSo, hocKyGanNhat, maHocPhanGoiY);

        return hocPhanClient.getHocPhanIn(maHocPhanGoiY);
    }

    // Thống kê số tín chỉ đăng ký và cải thiện theo lớp
    public List<ThongKeTinChiBySinhVien> thongKeKHHT(String maLop) {
        if(maLop == null || maLop.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        LopDTO lopDTO = profileClient.getLopByMaLop(maLop);
        if (lopDTO == null) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        List<String> maSoList = lopDTO.getDSSinhVien().stream().map(SinhVienDTO::getMaSo).toList();

        List<ThongKeTinChiBySinhVien> result = new ArrayList<>();

        maSoList.forEach(maSo ->{
            if (maSo == null || maSo.isEmpty()) {
                log.info("Mã số sinh viên không hợp lệ: {}", maSo);
            }
            else{
                ThongKeTinChiBySinhVien thongKe = new ThongKeTinChiBySinhVien();
                List<ThongKeTinChi> list = countKHHTGroupByHocKy(maSo);
                thongKe.setMaSo(maSo);
                thongKe.setThongKeTinChiList(list);
                if (!list.isEmpty()) {
                    result.add(thongKe);
                } else {
                    log.info("Không có kế hoạch học tập cho sinh viên: {}", maSo);
                }
            }
        });
        return result;
    }
    public void checkHocPhanTuyenQuyet(List<String> maHocPhanList, List<String> pendingList) {
        // Lấy thông tin học phần trong danh sách pending
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(pendingList);
        // Kiểm tra học phần tuyên quyết
        hocPhanDTOList.forEach(hocPhanDTO -> {
            if (hocPhanDTO.getHocPhanTienQuyet() != null && !hocPhanDTO.getHocPhanTienQuyet().isEmpty()) {
                String[] maHocPhanTuyenQuyet = hocPhanDTO.getHocPhanTienQuyet().split(",");
                for (String maHocPhan : maHocPhanTuyenQuyet) {
                    if (maHocPhanList.stream().noneMatch(kh -> kh.equals(maHocPhan))) {
                        throw new AppException(ErrorCode.CUSTOM_MESSAGE, "Học phần " + hocPhanDTO.getMaHp() + " không đáp ứng điều kiện tuyên quyết: " + maHocPhan);
                    }
                }
            }
        });
    }

    //    // Private methods
    private boolean isHocPhanExist(String maHocPhan) {
        try {
            hocPhanClient.getHocPhanById(maHocPhan);
            return true;
        } catch (AppException e) {
            log.warn("Học phần {} không tồn tại: {}", maHocPhan, e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Lỗi khi kiểm tra học phần {}: {}", maHocPhan, e.getMessage());
            return false;
        }
    }

    private boolean isKeHoachHocTapExist(String maSo, String maHocPhan) {
        return keHoachHocTapRepository.findByMaSoAndMaHocPhan(maSo, maHocPhan).isPresent();
    }


    private List<HocPhanDTO> getHocPhanFromKHHTList(List<KeHoachHocTapDTO> khhtList) {
        List<String> maHocPhanList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocPhan)
                .toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        if (hocPhanDTOList.isEmpty()) {
            throw new AppException(ErrorCode.HOCPHAN_NOTFOUND);
        }
        return hocPhanDTOList;
    }

    private List<HocKyDTO> getHocKyFromKHHTList(List<KeHoachHocTapDTO> khhtList) {
        List<Long> maHocKyList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocKy)
                .distinct()
                .toList();
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        if (hocKyDTOList.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return hocKyDTOList;
    }

    private List<KeHoachHocTapDetail> getKeHoachHocTapDetails(
            List<KeHoachHocTapDTO> khhtList,
            List<HocPhanDTO> hocPhanDTOList,
            List<HocKyDTO> hocKyDTOList) {
        Map<String, HocPhanDTO> hocPhanMap = hocPhanDTOList.stream()
                .collect(Collectors.toMap(HocPhanDTO::getMaHp, Function.identity()));
        Map<Long, HocKyDTO> hocKyMap = hocKyDTOList.stream()
                .collect(Collectors.toMap(HocKyDTO::getMaHocKy, Function.identity()));

        List<KeHoachHocTapDetail> khhtDetailList = new LinkedList<>();
        khhtList.forEach(khht -> {
            HocPhanDTO hocPhanDTO = hocPhanMap.get(khht.getMaHocPhan());
            HocKyDTO hocKyDTO = hocKyMap.get(khht.getMaHocKy());
            if (hocPhanDTO != null && hocKyDTO != null) {
                KeHoachHocTapDetail khhtDetail = new KeHoachHocTapDetail();
                khhtDetail.setHocKy(hocKyDTO);
                khhtDetail.setHocPhan(hocPhanDTO);
                khhtDetail.setNamHoc(hocKyDTO.getNamHoc());
                khhtDetail.setId(khht.getId());
                khhtDetail.setLoaiHocPhan(hocPhanDTO.getLoaiHp());
                khhtDetail.setHocPhanCaiThien(khht.isHocPhanCaiThien());
                khhtDetailList.add(khhtDetail);
            }
        });
        return khhtDetailList;
    }


}

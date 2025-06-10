package com.luanvan.kehoachhoctapservice.service;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTap;
import com.luanvan.kehoachhoctapservice.exception.AppException;
import com.luanvan.kehoachhoctapservice.exception.ErrorCode;
import com.luanvan.kehoachhoctapservice.model.KeHoachHocTapDTO;
import com.luanvan.kehoachhoctapservice.model.request.AddKHHTRequest;
import com.luanvan.kehoachhoctapservice.model.request.KeHoachHocTapRequest;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapCountResponse;
import com.luanvan.kehoachhoctapservice.repository.KeHoachHocTapRepository;
import com.luanvan.kehoachhoctapservice.repository.httpClient.HocPhanClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeHoachHocTapService {
    private final ModelMapper modelMapper;
    private final KeHoachHocTapRepository keHoachHocTapRepository;
    private final HocPhanClient hocPhanClient;


    public Long countKeHoachHocTapsByMaSo(KeHoachHocTapRequest request) {
        if (request.getMaSo() == null || request.getMaHocKy().isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }

        if (request.isHocPhanCaiThien()) {
            return keHoachHocTapRepository.countByMaSoAndHocPhanCaiThien(request.getMaSo(), true);
        }
        return keHoachHocTapRepository.countKeHoachHocTapsByMaSo(request.getMaSo());
    }

//  Lấy số lượng kế hoạch học tập theo mã số sinh viên và học kỳ
    public KeHoachHocTapCountResponse countKeHoachHocTapByMaSoAndMaHocKy(KeHoachHocTapRequest request) {
        if (request.getMaSo() == null || request.getMaSo().isEmpty() || request.getMaHocKy() == null || request.getMaHocKy().isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        Long soHocPhanDangKy = keHoachHocTapRepository.countKeHoachHocTapByMaSoAndMaHocKy(request.getMaSo(), request.getMaHocKy());
        Long soHocPhanCaiThien = keHoachHocTapRepository.countKeHoachHocTapByMaSoAndMaHocKyAndHocPhanCaiThien(request.getMaSo(), request.getMaHocKy(), true);
        return KeHoachHocTapCountResponse.builder()
                .soHocPhanCaiThien(soHocPhanCaiThien)
                .soHocPhanDangKy(soHocPhanDangKy)
                .build();
    }

    public KeHoachHocTapDTO create(AddKHHTRequest request) {
        //        Kiểm tra học phần này con tồn tại trong hệ thống không
        if (isHocPhanExist(request.getMaHocPhan())) {
            throw new AppException(ErrorCode.HOCPHAN_NOTFOUND);
        }
//      Kiểm tra học phần đang nhập có phải là học phần cải thiện không
        if (request.isHocPhanCaiThien() && !isKeHoachHocTapExist(request.getMaSo(), request.getMaHocPhan())) {
            throw new AppException(ErrorCode.KHHT_NOTFOUND);
        }

        KeHoachHocTap khht = modelMapper.map(request, KeHoachHocTap.class);

        keHoachHocTapRepository.save(khht);
        return modelMapper.map(khht, KeHoachHocTapDTO.class);
    }

    public List<KeHoachHocTapDTO> getKeHoachHocTapsByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        List<KeHoachHocTap> keHoachHocTaps = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);
        if (keHoachHocTaps.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }

        return keHoachHocTaps.stream()
                .map(khht -> modelMapper.map(khht, KeHoachHocTapDTO.class))
                .toList();
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

    public void delete(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        KeHoachHocTap existingKHHT = keHoachHocTapRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        keHoachHocTapRepository.delete(existingKHHT);
    }

    private boolean isHocPhanExist(String maHocPhan) {
        try {
            hocPhanClient.getHocPhanById(maHocPhan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isKeHoachHocTapExist(String maSo, String maHocPhan) {
        return keHoachHocTapRepository.findByMaSoAndMaHocPhan(maSo, maHocPhan).isPresent();
    }
}

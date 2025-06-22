package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.SinhVienDTO;
import com.luanvan.profileservice.dto.UserDTO;
import com.luanvan.profileservice.dto.request.CreateSinhVienRequest;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.SinhVien;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.LopRepository;
import com.luanvan.profileservice.repository.SinhVienRepository;
import com.luanvan.profileservice.repository.httpClient.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SinhVienService {
    private final SinhVienRepository sinhVienRepository;
    private final ModelMapper modelMapper;
    private final LopRepository lopRepository;
    private final ModelMapper sinhVienToDTOMapper;
    private final UserClient userClient;
    public void deleteSinhVien(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        sinhVienRepository.delete(sinhVien);
    }



    public SinhVienDTO updateSinhVien(SinhVienDTO sinhvienDTO) {

        if(sinhvienDTO == null || sinhvienDTO.getMaSo() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        SinhVien sv = sinhVienRepository.findById(sinhvienDTO.getMaSo())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        modelMapper.map(sinhvienDTO, sv);

        sinhVienRepository.save(sv);
        return sinhVienToDTOMapper.map(sv, SinhVienDTO.class);
    }

    @Transactional
    public SinhVienDTO createSinhVien(CreateSinhVienRequest request){
        Lop lop = lopRepository.findById(request.getMaLop())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        modelMapper.typeMap(SinhVienDTO.class, SinhVien.class)
                .addMappings(mapper ->
                        mapper.skip(SinhVien::setLop));
        SinhVien sv = new SinhVien();
        modelMapper.map(request, sv);
        sv.setLop(lop);
        sinhVienRepository.save(sv);
        return sinhVienToDTOMapper.map(sv, SinhVienDTO.class);
    }

    public SinhVienDTO findById(String maSo) {
        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        return modelMapper.map(sinhVien, SinhVienDTO.class);
    }
    public ProfileResponse getMyInfo(String maSo){
        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        UserDTO userDTO = userClient.getUserById(sinhVien.getMaSo());
        return ProfileResponse.builder()
                .hoTen(userDTO.getHoTen())
                .gioiTinh(userDTO.isGioiTinh())
                .ngaySinh(userDTO.getNgaySinh())
                .maSo(sinhVien.getMaSo())
                .maLop(sinhVien.getLop().getMaLop())
                .khoaHoc(sinhVien.getKhoaHoc())
                .tenNganh(sinhVien.getLop().getNganh().getTenNganh())
                .build();
    }
}

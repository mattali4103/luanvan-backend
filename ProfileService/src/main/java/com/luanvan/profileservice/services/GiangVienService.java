package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.GiangVienDTO;
import com.luanvan.profileservice.dto.UserDTO;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.entity.GiangVien;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.GiangVienRepository;
import com.luanvan.profileservice.repository.httpClient.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiangVienService {
    private final GiangVienRepository giangVienRepository;
    private final UserClient userClient;

    public ProfileResponse getMyInfo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        GiangVien giangVien = giangVienRepository.findById(maSo).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        UserDTO userDTO = userClient.getUserById(maSo);
        log.info("User info: {} {} {}", userDTO.getMaSo(), userDTO.getHoTen(), userDTO.getEmail());
        return ProfileResponse.builder()
                .maSo(maSo)
                .hoTen(userDTO.getHoTen())
                .email(userDTO.getEmail())
                .gioiTinh(userDTO.isGioiTinh())
                .ngaySinh(userDTO.getNgaySinh())
                .maKhoa(giangVien.getKhoa().getMaKhoa())
                .build();
    }

    public void deleteGiangVien(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        GiangVien giangVien = giangVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        giangVien.setKhoa(null);
        giangVienRepository.delete(giangVien);
    }

    public GiangVienDTO updateGiangVien(GiangVienDTO giangVienDTO) {
        if (giangVienDTO == null || giangVienDTO.getMaSo() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }


        GiangVien gv = giangVienRepository.findById(giangVienDTO.getMaSo())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(giangVienDTO, gv);

        giangVienRepository.save(gv);
        return modelMapper.map(gv, GiangVienDTO.class);
    }

    public GiangVienDTO createGiangVien(GiangVienDTO giangVienDTO) {
        ModelMapper modelMapper = new ModelMapper();
        GiangVien gv = new GiangVien();

        modelMapper.typeMap(GiangVienDTO.class, GiangVien.class)
                .addMappings(mapper -> mapper.skip(GiangVien::setKhoa));
        modelMapper.map(giangVienDTO, gv);

        giangVienRepository.save(gv);
        return modelMapper.map(gv, GiangVienDTO.class);
    }

    public GiangVienDTO findById(String maSo) {
        GiangVien giangVien = giangVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(giangVien, GiangVienDTO.class);
    }
}

package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.AdminDTO;
import com.luanvan.profileservice.dto.UserDTO;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.entity.Admin;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.AdminRepository;
import com.luanvan.profileservice.repository.httpClient.UserClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserClient userClient;

    public AdminService(AdminRepository adminRepository, UserClient userClient) {
        this.adminRepository = adminRepository;
        this.userClient = userClient;
    }

    public void deleteAdmin(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        Admin admin = adminRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        adminRepository.delete(admin);
    }
    public ProfileResponse getMyInfo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        adminRepository.findById(maSo).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        UserDTO userDTO = userClient.getUserById(maSo);
        return ProfileResponse.builder()
                .maSo(maSo)
                .hoTen(userDTO.getHoTen())
                .gioiTinh(userDTO.isGioiTinh())
                .ngaySinh(userDTO.getNgaySinh())
                .build();
    }

    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        if (adminDTO == null || adminDTO.getMaSo() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ModelMapper modelMapper = new ModelMapper();
        Admin admin = adminRepository.findById(adminDTO.getMaSo())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        modelMapper.map(adminDTO, admin);
        adminRepository.save(admin);
        return modelMapper.map(admin, AdminDTO.class);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Admin admin = new Admin();
        modelMapper.map(adminDTO, admin);
        adminRepository.save(admin);
        return modelMapper.map(admin, AdminDTO.class);
    }

    public AdminDTO findById(String maSo) {
        Admin admin = adminRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(admin, AdminDTO.class);
    }
}

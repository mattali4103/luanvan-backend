package com.luanvan.userservice.service;

import com.luanvan.learningprogress.exception.AppException;
import com.luanvan.learningprogress.exception.ErrorCode;
import com.luanvan.userservice.entity.Role;
import com.luanvan.userservice.entity.User;
import com.luanvan.userservice.model.Request.CreateSinhVienRequest;
import com.luanvan.userservice.model.dto.UserDTO;
import com.luanvan.userservice.repository.RoleRepository;
import com.luanvan.userservice.repository.UserRepository;
import com.luanvan.userservice.repository.httpClient.ProfileClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ProfileClient profileClient;

    public UserDTO create(CreateSinhVienRequest request) {
        if(userRepository.existsById(request.getMaSo())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findByName("SINHVIEN");
        user.setRole(role);
        userRepository.save(user);
        var createSinhVienRequest = modelMapper.map(request, CreateSinhVienRequest.class);
        var createProfile = profileClient.createSinhVien(createSinhVienRequest);

        log.info("createSinhVienRequest:{}", createProfile);
        return modelMapper.map(user, UserDTO.class);
    }
}

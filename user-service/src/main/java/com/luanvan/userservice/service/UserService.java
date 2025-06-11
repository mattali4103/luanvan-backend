package com.luanvan.userservice.service;


import com.luanvan.userservice.entity.Role;
import com.luanvan.userservice.entity.User;
import com.luanvan.userservice.exception.AppException;
import com.luanvan.userservice.exception.ErrorCode;
import com.luanvan.userservice.model.Request.CreateSinhVienRequest;
import com.luanvan.userservice.model.dto.UserDTO;
import com.luanvan.userservice.repository.RoleRepository;
import com.luanvan.userservice.repository.UserRepository;
import com.luanvan.userservice.repository.httpClient.ProfileClient;
import com.luanvan.userservice.utils.SecurityUltils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ProfileClient profileClient;

    @Transactional
    public UserDTO create(CreateSinhVienRequest request) {
        if (userRepository.existsById(request.getMaSo())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();
//        String currentRole = SecurityUltils.getCurrentUserRole();
//        if(currentRole.equals("SCOPE_ADMIN")){
//            request.getRoles().forEach(role -> {
//                Role userRole = roleRepository.findByName(role.getName());
//                if (userRole != null) {
//                    roles.add(role);
//                }
//            });
//        }
//        else{
//            roles.add(roleRepository.findByName("SINHVIEN"));
//        }
        request.getRoles().forEach(roleDTO -> {
            Role role = roleRepository.findByName(roleDTO.getName());
            roles.add(role);
        });
        user.setRoles(roles);

        var createSinhVienRequest = modelMapper.map(request, CreateSinhVienRequest.class);
        profileClient.createSinhVien(createSinhVienRequest);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getUserByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        User user = userRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        return modelMapper.map(user, UserDTO.class);
    }
}

package com.luanvan.userservice.model.dto;

import com.luanvan.userservice.entity.Admin;
import com.luanvan.userservice.entity.GiangVien;
import com.luanvan.userservice.entity.Role;
import com.luanvan.userservice.entity.SinhVien;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {
    Long id;
    String password;
    SinhVien sinhVien;
    GiangVien giangvien;
    Admin admin;
    List<Role> roles;
}

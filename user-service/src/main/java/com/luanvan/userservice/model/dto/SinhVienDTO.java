package com.luanvan.userservice.model.dto;


import com.luanvan.userservice.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SinhVienDTO {
    private String MSSV;
    private String HoTen;
    private String Email;
    private LocalDate NgaySinh;
    private boolean GioiTinh;
    private String Lop;
    private String Nganh;
    private String KhoaHoc;
    private String Khoa;
    UserDTO User;
}

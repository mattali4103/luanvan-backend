package com.luanvan.userservice.model.Request;

import com.luanvan.userservice.entity.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSinhVienRequest {
    String maSo;
    String password;
    String hoTen;
    String email;
    String maLop;
    LocalDate ngaySinh;
    boolean gioiTinh;
    String khoaHoc;
    List<Role> roles;
    Long maNganh;
}

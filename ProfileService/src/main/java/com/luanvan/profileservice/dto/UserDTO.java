package com.luanvan.profileservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {
    String maSo;
    String password;
    String hoTen;
    String email;
    LocalDate ngaySinh;
    boolean gioiTinh;
}
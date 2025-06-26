package com.luanvan.profileservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProfileResponse {
    String maSo;
    String khoaHoc;
    Long maNganh;
    String tenNganh;
    String hoTen;
    LocalDate ngaySinh;
    boolean gioiTinh;
    String maLop;
    Long maSoThue;
    Long soTk;
    Long maKhoa;
}

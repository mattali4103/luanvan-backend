package com.luanvan.profileservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

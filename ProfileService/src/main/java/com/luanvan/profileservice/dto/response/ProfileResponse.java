package com.luanvan.profileservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luanvan.profileservice.dto.LopDTO;
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
    String soDienThoai;
    String email;
    String diaChi;
    String queQuan;
    String avatarUrl;
    String danToc;
    String tonGiao;
    String cccd;
    String ngayCapCCCD;
    String noiCapCCCD;
    LopDTO lop;
    String hoTenCha;
    String hoTenMe;
    String soDienThoaiNguoiThan;
}

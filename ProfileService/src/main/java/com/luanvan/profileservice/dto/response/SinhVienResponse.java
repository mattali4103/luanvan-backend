package com.luanvan.profileservice.dto.response;

import com.luanvan.profileservice.dto.LopDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienResponse {
    String maSo;
    String khoaHoc;
    String maLop;
    String tenNganh;
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
}
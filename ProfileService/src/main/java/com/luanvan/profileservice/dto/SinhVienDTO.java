package com.luanvan.profileservice.dto;


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
public class SinhVienDTO {
    String maSo;
    String khoaHoc;
    String maLop;
    LopDTO lopDTO;
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

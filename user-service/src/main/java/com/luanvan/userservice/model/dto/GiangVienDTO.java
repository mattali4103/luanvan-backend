package com.luanvan.userservice.model.dto;


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
public class GiangVienDTO {
    Long id;
    String ma_gv;
    String ten_gv;
    String email;
    Long sdt;
    LocalDate ngay_sinh;
    Boolean gioi_tinh;
    String ma_so_thue;
    int so_tk;
    String khoa;
}

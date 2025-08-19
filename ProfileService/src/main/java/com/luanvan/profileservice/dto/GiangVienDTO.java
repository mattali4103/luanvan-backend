package com.luanvan.profileservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GiangVienDTO {
    String maSo;
    String hoTen;
    Long maSoThue;
    Long soTk;
    String email;
    String soDienThoai;
    // Không include DSLop để tránh circular reference
}

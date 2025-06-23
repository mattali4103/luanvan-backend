package com.luanvan.profileservice.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiangVienDTO {
    String maSo;
    Long maSoThue;
    Long soTk;
    Long maKhoa;
    KhoaDTO khoaDTO;
}

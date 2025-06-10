package com.luanvan.profileservice.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiemDTO {
    Long id;
    String maHocKy;
    Double diemTB;
    String sinhVienMaSo;
}

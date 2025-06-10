package com.luanvan.ketquahoctapservice.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KetQuaHocTapDTO {
    Long id;
    String diem_chu;
    Float diem_so;
    String dieu_kien;
    String ma_hoc_ky;
    String maSo;
    String ma_hoc_phan;
    Long maNhomHP;
}

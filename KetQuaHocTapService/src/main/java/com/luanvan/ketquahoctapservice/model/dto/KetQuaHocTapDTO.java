package com.luanvan.ketquahoctapservice.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KetQuaHocTapDTO {
    Long id;
    String diemChu;
    Float diemSo;
    String dieuKien;
    Long maHocKy;
    String maSo;
    String maHp;
    Long soTinChi;
    Long maNhomHP;
}

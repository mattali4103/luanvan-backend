package com.luanvan.hocphanservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhomHpDTO {
    String maNhomHP;
    int tietBatdau;
    int tietKetthuc;
    int siSo;
    int siSoCon;
    HocPhanDTO hocPhanDTO;
    String MaSoGiangVien;
    PhongHocDTO PhongHocDTO;
    HocKyDTO hocKyDTO;
}
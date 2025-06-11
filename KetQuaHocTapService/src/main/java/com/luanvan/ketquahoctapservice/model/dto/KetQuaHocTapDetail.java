package com.luanvan.ketquahoctapservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KetQuaHocTapDetail {
    Long id;
    String diemChu;
    Float diemSo;
    String dieuKien;
    HocPhanDTO hocPhanDTO;
    HocKyDTO hocKyDTO;
    NamHocDTO namHocDTO;
    Long maNhomHP;
    int soTinChi;

}

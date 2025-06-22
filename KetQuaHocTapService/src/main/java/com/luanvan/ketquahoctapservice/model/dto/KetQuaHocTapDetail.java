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
    Double diemSo;
    boolean dieuKien;
    Long maNhomHP;
    Long soTinChi;
    HocPhanDTO hocPhan;
    HocKyDTO hocKy;
}

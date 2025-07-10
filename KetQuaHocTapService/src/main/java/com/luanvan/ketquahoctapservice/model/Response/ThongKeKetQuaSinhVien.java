package com.luanvan.ketquahoctapservice.model.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ThongKeKetQuaSinhVien {
    String maSo;
    Long soTinChiTichLuy;
    Long soTinChiCaiThien;
    Double diemTBTichLuy;
    String xepLoai;
    CanhBaoHocVu canhBaoHocVu;
}

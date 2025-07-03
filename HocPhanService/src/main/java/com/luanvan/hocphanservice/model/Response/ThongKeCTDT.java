package com.luanvan.hocphanservice.model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ThongKeCTDT {
    String khoaHoc;
    Long maNganh;
    Long tongSoHocPhan;
    Long tongSoTinChi;
    Long tongSoNhomTuChon;
}

package com.luanvan.kehoachhoctapservice.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeHoachHocTapResponse {
    Long id;
    String maSo;
    String maHocKy;
    String maHocPhan;
    Boolean hocPhanCaiThien;
    Long count;
}

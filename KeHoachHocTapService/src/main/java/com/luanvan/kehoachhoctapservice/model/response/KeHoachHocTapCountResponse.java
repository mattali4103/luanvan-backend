package com.luanvan.kehoachhoctapservice.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeHoachHocTapCountResponse {
//    Long soTinChiDangKy;
    Long soTinChiCaiThien;
}

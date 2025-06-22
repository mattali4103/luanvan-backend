package com.luanvan.kehoachhoctapservice.model.response;

import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTinChi {
    HocKyDTO hocKy;
    Long soTinChiDangKy;
    Long soTinChiCaiThien;
}

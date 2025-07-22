package com.luanvan.ketquahoctapservice.model.Response;

import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTinChi {
    HocKyDTO hocKy;
    Long soTinChiTichLuy;
    Long soTinChiRot;
}

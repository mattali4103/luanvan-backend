package com.luanvan.kehoachhoctapservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTinChi {
    String maSo;
    HocKyDTO hocKy;
    Long soTinChiDangKy;
    Long soTinChiCaiThien;
}

package com.luanvan.ketquahoctapservice.model.Response;

import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TinChiResponse {
    Long tongSoTinChi;
    Long soTinChiTichLuy;
    Long soTinChiCaiThien;

    public TinChiResponse(long l, long l1, long l2, long l3) {
    }
}


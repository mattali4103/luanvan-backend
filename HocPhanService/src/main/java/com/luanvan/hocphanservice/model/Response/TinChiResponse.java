package com.luanvan.hocphanservice.model.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TinChiResponse {
    Long tongSoTinChi;
    Long soTinChiTichLuy;
    Long soTinChiCaiThien;
}

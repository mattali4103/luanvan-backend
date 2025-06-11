package com.luanvan.kehoachhoctapservice.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocPhanRequest {
    String maSo;
    String khoaHoc;
    String loaiHp;
}

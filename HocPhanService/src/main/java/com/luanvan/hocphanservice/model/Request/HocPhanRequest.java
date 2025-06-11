package com.luanvan.hocphanservice.model.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocPhanRequest {
    String maSo;
    String khoaHoc;
}

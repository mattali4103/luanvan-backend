package com.luanvan.kehoachhoctapservice.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddKHHTRequest {
    String maSo;
    String maHocKy;
    String maHocPhan;
    boolean hocPhanCaiThien;
}

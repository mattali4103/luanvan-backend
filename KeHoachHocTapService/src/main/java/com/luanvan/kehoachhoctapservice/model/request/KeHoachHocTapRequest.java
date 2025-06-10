package com.luanvan.kehoachhoctapservice.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeHoachHocTapRequest {
    String maSo;
    String maHocKy;
    boolean hocPhanCaiThien;
}

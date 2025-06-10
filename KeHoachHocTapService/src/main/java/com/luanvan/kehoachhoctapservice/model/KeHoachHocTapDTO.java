package com.luanvan.kehoachhoctapservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KeHoachHocTapDTO {
    Long id;
    String maSo;
    String maHocKy;
    String maHocPhan;
    boolean hocPhanCaiThien;
}


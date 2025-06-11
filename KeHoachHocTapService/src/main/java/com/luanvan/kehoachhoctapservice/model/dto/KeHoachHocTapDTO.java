package com.luanvan.kehoachhoctapservice.model.dto;

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
    Long maHocKy;
    String maHocPhan;
    boolean hocPhanCaiThien;
}


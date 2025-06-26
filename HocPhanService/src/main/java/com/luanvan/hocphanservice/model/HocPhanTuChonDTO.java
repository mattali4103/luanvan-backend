package com.luanvan.hocphanservice.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocPhanTuChonDTO {
    Long id;
    String tenNhom;
    Long tinChiYeuCau;
    String khoaHoc;
    Long maNganh;
    List<HocPhanDTO> hocPhanTuChonList;
}

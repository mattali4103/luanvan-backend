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
public class ChuongTrinhDaoTaoDTO {
    Long id;
    Long maNganh;
    String noiDung;
    String khoaHoc;
    List<HocPhanDTO> hocPhanList;
}

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
    String khoaHoc;
    Long maNganh;
    String noiDung;
    List<HocPhanDTO> hocPhanList;
}

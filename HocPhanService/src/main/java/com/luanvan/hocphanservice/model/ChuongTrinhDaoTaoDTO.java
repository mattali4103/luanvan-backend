package com.luanvan.hocphanservice.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuongTrinhDaoTaoDTO {
    String maNganh;
    String noiDung;
    String khoaHoc;
    List<HocPhanDTO> hocPhanList;
}

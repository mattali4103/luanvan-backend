package com.luanvan.hocphanservice.model;


import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import com.luanvan.hocphanservice.model.Request.ChuongTrinhDaoTaoRequest;
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

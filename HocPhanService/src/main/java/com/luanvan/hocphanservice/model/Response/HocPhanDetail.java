package com.luanvan.hocphanservice.model.Response;

import com.luanvan.hocphanservice.model.HocKyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocPhanDetail {
    String maHp;
    String tenHp;
    int tinChi;
    String moTa;
    String loaiHp;
    HocKyDTO hocKy;
}

package com.luanvan.hocphanservice.model;
import com.luanvan.hocphanservice.entity.NhomHP;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhongHocDTO {
    String maPhong;
    String maNhaHoc;
    int sucChua;
    boolean tietHoc;
    List<NhomHP> dsNhomHP;
}

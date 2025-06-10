package com.luanvan.ketquahoctapservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class KetQuaHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String diem_chu;
    Float diem_so;
    String dieu_kien;
    String ma_hoc_ky;
    String maSo;
    String ma_hoc_phan;
    Long maNhomHP;
}

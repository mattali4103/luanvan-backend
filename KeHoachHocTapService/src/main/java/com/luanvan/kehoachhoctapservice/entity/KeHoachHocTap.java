package com.luanvan.kehoachhoctapservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ke_hoach_hoc_tap")
public class KeHoachHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String maSo;
    String maHocKy;
    String maHocPhan;
    boolean hocPhanCaiThien;
}

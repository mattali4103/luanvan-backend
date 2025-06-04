package com.luanvan.learningprogress.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "ma_hoc_ky")
    @JsonBackReference
    HocKy hoc_ky;

    Long sinh_vien_id;

    @ManyToOne
    @JoinColumn(name = "ma_hoc_phan")
    @JsonBackReference
    HocPhan hoc_phan;

    @ManyToOne
    @JoinColumn(name = "ma_nhom_hp")
    @JsonBackReference
    NhomHP nhom_hp;
}

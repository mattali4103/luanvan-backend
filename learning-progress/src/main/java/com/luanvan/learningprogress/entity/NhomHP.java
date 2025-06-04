package com.luanvan.learningprogress.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "nhom_hoc_phan")
public class NhomHP {
    @Id
    Long ma_nhom_hp;

    String nam_hoc;

    int tiet_bat_dau;
    int tiet_ket_thuc;

    int si_so;
    int si_so_hien_tai;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_hoc_phan")
    HocPhan hoc_phan;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_hoc_ky")
    HocKy hoc_ky;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_phong_hoc")
    PhongHoc phong_hoc;
}

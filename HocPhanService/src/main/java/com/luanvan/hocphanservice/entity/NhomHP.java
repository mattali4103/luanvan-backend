package com.luanvan.hocphanservice.entity;

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
    String maNhomHP;
    int tietBatdau;
    int tietKetthuc;

    int siSo;
    int siSoCon;

    String maGiangVien;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_hoc_phan")
    HocPhan hocPhan;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_hoc_ky")
    HocKy hocKy;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_phong_hoc")
    PhongHoc phongHoc;



}
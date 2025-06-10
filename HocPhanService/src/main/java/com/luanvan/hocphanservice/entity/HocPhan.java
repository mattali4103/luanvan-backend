package com.luanvan.hocphanservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
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
@Table(name = "hoc_phan")
public class HocPhan {
    @Id
    String maHp;
    String tenHp;
    int tinChi;
    String moTa;
    String loaiHp;
    String hocPhanTienQuyet;
    @OneToMany(mappedBy = "hocPhan")
    @JsonBackReference
    List<NhomHP> dsNhomHp;

    @ManyToMany(mappedBy = "hocPhanList")
    @Nullable
    @JsonBackReference
    List<ChuongTrinhDaoTao> chuongTrinhDaoTaoList;
}
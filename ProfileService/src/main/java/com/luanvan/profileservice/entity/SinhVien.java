package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sinh_vien")
public class SinhVien {
    @Id
    String maSo;
    String khoaHoc;

    // Thông tin liên lạc
    String soDienThoai;
    String email;
    String diaChi;
    String queQuan;
    String avatarUrl;

    // Thông tin cá nhân
    String danToc;
    String tonGiao;

    // Thông tin CCCD/CMND
    String cccd;

    @Column(name = "ngay_cap_cccd")
    LocalDate ngayCapCCCD;

    @Column(name = "noi_cap_cccd")
    String noiCapCCCD;

    @ManyToOne
    @JoinColumn(name = "maLop")
    @JsonManagedReference
    Lop lop;
}
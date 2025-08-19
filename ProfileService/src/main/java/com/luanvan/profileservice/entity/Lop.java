package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lop {
    @Id
    String maLop;
    String tenLop;
    Long siSo;
    Long siSoCon;

    @ManyToOne
    @JoinColumn(name = "chu_nhiem")
    @JsonBackReference("giang-vien-lop")
    GiangVien chuNhiem;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "maLop")
    @JsonBackReference
    List<SinhVien> DSSinhVien;

    @ManyToOne
    @JoinColumn(name = "maNganh")
    @JsonBackReference("nganh-lop")
    Nganh nganh;
}

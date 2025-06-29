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
    String chuNhiem;
    Long siSo;
    Long siSoCon;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "maLop")
    @JsonBackReference
    List<SinhVien> DSSinhVien;

    @ManyToOne
    @JoinColumn(name = "maNganh")
    @JsonBackReference
    Nganh nganh;
}

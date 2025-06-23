package com.luanvan.hocphanservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "nam_hoc")
public class NamHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String namBatDau;
    String namKetThuc;
    @OneToMany(mappedBy = "namHoc")
    @JsonBackReference
    List<HocKy> hocKy;
}

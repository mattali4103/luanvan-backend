package com.luanvan.learningprogress.entity;

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
    Long id;
    String nam_bat_dau;
    String nam_ket_thuc;
    @OneToMany(mappedBy = "nam_hoc")
    List<HocKy> hoc_ky;
}

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
@Table(name = "hoc_phan")
public class HocPhan {
    @Id
    String ma_hp;
    String ten_hp;
    int tin_chi;
    String mo_ta;
    String loai_hp;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "hoc_ky_ma_hoc_ky")
    HocKy hoc_ky;
    @OneToMany(mappedBy = "hoc_phan")
    List<NhomHP> ds_nhom_hp;

}
package com.luanvan.learningprogress.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hoc_ky")
public class HocKy {
    @Id
    String ma_hoc_ky;
    LocalDate ngay_bat_dau_hoc;
    LocalDate ngay_ket_thuc;


    @ManyToOne
    @JoinColumn(name = "nam_hoc_id")
    NamHoc nam_hoc;

    @OneToMany(mappedBy = "hoc_ky")
    List<KeHoachHocTap> ds_ke_hoach_hoc_tap;

    @OneToMany(mappedBy = "hoc_ky")
    List<KetQuaHocTap> ds_ket_qua_hoc_tap;

    @OneToMany(mappedBy = "hoc_ky")
    List<NhomHP> ds_nhom_hp;


}

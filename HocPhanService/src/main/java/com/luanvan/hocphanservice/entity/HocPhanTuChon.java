package com.luanvan.hocphanservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocPhanTuChon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String tenNhom;
    Long tinChiYeuCau;
    @ManyToMany
    @JoinTable(
            name = "hoc_phan_tu_chon_hoc_phan",
            joinColumns = @JoinColumn(name = "id_nhom"),
            inverseJoinColumns = @JoinColumn(name = "ma_hoc_phan")
    )
    List<HocPhan> hocPhanTuChonList;

    @ManyToOne
    @JoinColumn(name = "chuong_trinh_dao_tao_id")
    ChuongTrinhDaoTao chuongTrinhDaoTao;
}

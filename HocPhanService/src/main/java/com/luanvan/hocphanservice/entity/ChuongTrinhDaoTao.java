package com.luanvan.hocphanservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ChuongTrinhDaoTao {
    @Id
    String khoaHoc;
    Long maNganh;
    @ManyToMany
    @JoinTable(
        name = "ctdt_hp",
        joinColumns = @JoinColumn(name = "chuong_trinh_dao_tao_id"),
        inverseJoinColumns = @JoinColumn(name = "hoc_phan_id")
    )
    @JsonBackReference
    List<HocPhan> hocPhanList;
    String noiDung;

    @OneToMany(mappedBy = "chuongTrinhDaoTao", cascade = CascadeType.ALL, orphanRemoval = true)
    List<HocPhanTuChon> nhomHocPhanTuChon;

}

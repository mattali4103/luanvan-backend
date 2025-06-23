package com.luanvan.hocphanservice.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long maHocKy;
    String tenHocKy;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;


    @ManyToOne
    @JoinColumn(name = "nam_hoc_id")
    @JsonBackReference
    NamHoc namHoc;

    @OneToMany(mappedBy = "hocKy")
    @JsonBackReference
    List<NhomHP> DSNhomHP;
}


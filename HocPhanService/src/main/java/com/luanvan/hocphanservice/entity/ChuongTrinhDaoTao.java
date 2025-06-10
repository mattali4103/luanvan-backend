package com.luanvan.hocphanservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    String maNganh;
    @ManyToMany(mappedBy = "chuongTrinhDaoTaoList")
    @JsonBackReference
    List<HocPhan> hocPhanList;
    String khoaHoc;
    String noiDung;
}

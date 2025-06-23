package com.luanvan.hocphanservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PhongHoc {
    @Id
    String maPhong;
    String maNhaHoc;
    int sucChua;
    boolean tietHoc;
    @OneToMany(mappedBy = "phongHoc")
    @JsonBackReference
    List<NhomHP> dsNhomHp;
}



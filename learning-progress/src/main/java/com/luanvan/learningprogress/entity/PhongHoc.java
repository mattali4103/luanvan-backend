package com.luanvan.learningprogress.entity;

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
    String ma_phong;
    String ma_nha_hoc;
    int suc_chua;
    int tiet_hoc;
    int tiet_trong;
    @OneToMany(mappedBy = "phong_hoc")
    List<NhomHP> ds_nhom_hp;
}

package com.luanvan.hocphanservice.model;


import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class HocPhanDTO {
    @Id
    String maHp;
    String tenHp;
    int tinChi;
    String moTa;
    String loaiHp;
    String hocPhanTienQuyet;
}
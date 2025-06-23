package com.luanvan.hocphanservice.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class HocPhanDTO {
    String maHp;
    String tenHp;
    int tinChi;
    String moTa;
    String loaiHp;
    String hocPhanTienQuyet;
}
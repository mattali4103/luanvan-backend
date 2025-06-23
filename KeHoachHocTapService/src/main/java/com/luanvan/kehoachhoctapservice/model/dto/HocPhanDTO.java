package com.luanvan.kehoachhoctapservice.model.dto;

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
    String maHp;
    String tenHp;
    int tinChi;
    String moTa;
    String loaiHp;
    String hocPhanTienQuyet;
}

package com.luanvan.hocphanservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocKyDTO {
    Long maHocKy;
    String tenHocKy;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    NamHocDTO namHocDTO;
}

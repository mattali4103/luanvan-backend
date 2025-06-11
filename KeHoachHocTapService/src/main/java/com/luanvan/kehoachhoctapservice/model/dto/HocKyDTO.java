package com.luanvan.kehoachhoctapservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocKyDTO {
    Long maHocKy;
    String tenHocKy;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    NamHocDTO namHocDTO;
}

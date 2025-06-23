package com.luanvan.hocphanservice.model.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocKyRequest {
    Long maHocKy;
    String tenHocKy;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    Long namHocId;
}
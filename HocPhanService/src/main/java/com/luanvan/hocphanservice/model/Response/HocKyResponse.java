package com.luanvan.hocphanservice.model.Response;

import com.luanvan.hocphanservice.model.NamHocDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocKyResponse {
    Long maHocKy;
    String tenHocKy;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    NamHocDTO namHoc;
}

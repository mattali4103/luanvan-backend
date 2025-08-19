package com.luanvan.profileservice.dto;

import com.luanvan.profileservice.dto.response.SinhVienResponse;
import com.luanvan.profileservice.entity.SinhVien;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateDTO {
    Long id;
    String tenChungChi;
    LocalDate ngayCap;
    String imageUrl;
    SinhVienResponse sinhVien;
}

package com.luanvan.profileservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String tenChungChi;
    LocalDate ngayCap;
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "maSo")
    SinhVien sinhVien;

}

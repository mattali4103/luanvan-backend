package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "giang_vien")
public class GiangVien {
    @Id
    String maSo;
    Long maSoThue;
    Long soTk;
    @ManyToOne
    @JoinColumn(name = "maKhoa")
    @JsonBackReference
    Khoa khoa;
}
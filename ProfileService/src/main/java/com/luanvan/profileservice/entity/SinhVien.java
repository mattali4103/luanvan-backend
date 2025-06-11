package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sinh_vien")
public class SinhVien {
    @Id
    String maSo;
    String khoaHoc;
    @ManyToOne
    @JoinColumn(name = "maLop")
    @JsonManagedReference
    Lop lop;
}
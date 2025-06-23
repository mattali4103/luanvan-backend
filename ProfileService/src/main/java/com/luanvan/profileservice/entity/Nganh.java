package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "nganh")
public class Nganh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long maNganh;
    String tenNganh;

    @OneToMany(mappedBy = "nganh")
    @JsonBackReference
    List<Lop> dsLop;

    @ManyToOne
    @JoinColumn(name = "maKhoa")
    @JsonBackReference
    Khoa khoa;
}

package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("nganh-lop")
    List<Lop> dsLop;

    @ManyToOne
    @JoinColumn(name = "maKhoa")
    @JsonBackReference("khoa-nganh")
    Khoa khoa;
}

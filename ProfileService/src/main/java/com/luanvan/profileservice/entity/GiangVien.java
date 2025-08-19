package com.luanvan.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    @OneToMany(mappedBy = "chuNhiem")
    @JsonManagedReference("giang-vien-lop")
    List<Lop> DSLop;
}
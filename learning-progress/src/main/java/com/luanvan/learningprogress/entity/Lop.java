package com.luanvan.learningprogress.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "lop")
public class Lop {
    @Id
    String ma_lop;
    String ten_lop;
    int si_so;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_nganh")
    Nganh nganh;
}

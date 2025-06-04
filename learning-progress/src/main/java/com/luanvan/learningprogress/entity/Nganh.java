package com.luanvan.learningprogress.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "nganh")
public class Nganh {
    @Id
    String ma_nganh;
    String ten_nganh;
    @OneToMany(mappedBy = "nganh")
    List<Lop> ds_lop;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_khoa")
    Khoa khoa;

}

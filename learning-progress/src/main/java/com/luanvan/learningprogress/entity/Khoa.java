package com.luanvan.learningprogress.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "khoa")
public class Khoa {
    @Id
    String ma_khoa;
    String ten_khoa;
    @OneToMany(mappedBy = "khoa")
    List<Nganh> nganh;

}

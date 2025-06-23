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
public class Lop {
    @Id
    String maLop;
    String tenLop;
    String chuNhiem;

    @ManyToOne
    @JoinColumn(name = "maNganh")
    @JsonBackReference
    Nganh nganh;

}

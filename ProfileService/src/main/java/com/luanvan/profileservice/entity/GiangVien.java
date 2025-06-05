package com.luanvan.profileservice.entity;

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
    Long ma_so_thue;
    Long so_tk;
    Long ma_khoa;
}
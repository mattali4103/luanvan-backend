package com.luanvan.hocphanservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class HocKyHienTai {
    @Id
    Long maHocKy;
}

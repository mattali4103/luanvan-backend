package com.luanvan.userservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    String maSo;
    String password;

    private String HoTen;
    private String Email;
    private LocalDate NgaySinh;
    private boolean GioiTinh;
    @ManyToMany
    Set<Role> roles;
    @OneToMany(mappedBy = "user")
    List<GiangVien> giangVien;

    @OneToOne(mappedBy = "user")
    Admin admin;

    @OneToMany(mappedBy = "user")
    List<SinhVien> sinhVien;
}

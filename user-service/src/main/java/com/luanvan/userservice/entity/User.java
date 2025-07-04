package com.luanvan.userservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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

    private String hoTen;
    private String email;
    private LocalDate ngaySinh;
    private boolean gioiTinh;

    @ManyToMany
    Set<Role> roles;
}

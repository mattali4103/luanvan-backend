package com.luanvan.userservice.entity;



import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    Long id;
    String ten_gv;
    String email;
    Long sdt;
    LocalDate ngay_sinh;
    Boolean gioi_tinh;
    String ma_so_thue;
    int so_tk;
    String ma_khoa;

    @ManyToOne
    @JoinColumn(name = "gv_id")
    User user;
}

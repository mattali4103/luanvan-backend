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
@Table(name = "ke_hoach_hoc_tap")
public class KeHoachHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long sinhVien_id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ma_hoc_ky")
    HocKy hoc_ky;
    @OneToMany(mappedBy = "ke_hoach_hoc_tap")
    List<HocPhan> hoc_phan;

}

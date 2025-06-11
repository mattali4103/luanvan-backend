package com.luanvan.ketquahoctapservice.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class KetQuaHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String diemChu;
    Double diemSo;
    boolean dieuKien;
    Long maHocKy;
    String maSo;
    String maHp;
    Long soTinChi;
    Long maNhomHP;

    private static final Map<String, Double> DIEM_SO = new HashMap<>() {{
        put("A", 4.0);
        put("B+", 3.5);
        put("B", 3.0);
        put("C+", 2.5);
        put("C", 2.0);
        put("D+", 1.5);
        put("D", 1.0);
        put("F", 0.0);
    }};
    public Double getDiem4() {
        return DIEM_SO.getOrDefault(diemChu != null ? diemChu.toUpperCase() : null, 0.0);
    }
}

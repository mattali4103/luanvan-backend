package com.luanvan.hocphanservice.model.Response;

import com.luanvan.hocphanservice.model.HocKyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NamHocResponse {
    Long id;
    String namBatDau;
    String namKetThuc;
    List<HocKyDTO> hocKy;
    @Override
    public String toString() {
        return namBatDau + "-" + namKetThuc;
    }
}

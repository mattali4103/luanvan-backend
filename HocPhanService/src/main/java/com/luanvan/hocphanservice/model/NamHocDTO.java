package com.luanvan.hocphanservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NamHocDTO {
    Long id;
    String namBatDau;
    String namKetThuc;
    @Override
    public String toString() {
        return namBatDau + "-" + namKetThuc;
    }
}

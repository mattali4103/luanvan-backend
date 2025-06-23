package com.luanvan.ketquahoctapservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NamHocDTO {
    Long id;
    String namBatDau;
    String namKetThuc;

}

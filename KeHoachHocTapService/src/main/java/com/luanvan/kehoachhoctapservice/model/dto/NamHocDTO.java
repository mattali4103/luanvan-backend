package com.luanvan.kehoachhoctapservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NamHocDTO {
    Long id;
    String namBatDau;
    String namKetThuc;
}

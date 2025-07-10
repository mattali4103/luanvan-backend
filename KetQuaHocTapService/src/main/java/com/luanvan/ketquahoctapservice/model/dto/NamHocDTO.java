package com.luanvan.ketquahoctapservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NamHocDTO {
    Long id;
    String namBatDau;
    String namKetThuc;
    List<HocKyDTO> hocKyList;
}

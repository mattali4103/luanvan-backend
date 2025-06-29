package com.luanvan.kehoachhoctapservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LopDTO {
    String maLop;
    String tenLop;
    String chuNhiem;
    List<SinhVienDTO> DSSinhVien;
}
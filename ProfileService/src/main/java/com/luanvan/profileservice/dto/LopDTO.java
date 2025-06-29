package com.luanvan.profileservice.dto;

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
    Long siSo;
    Long siSoCon;
    List<SinhVienDTO> DSSinhVien;
}

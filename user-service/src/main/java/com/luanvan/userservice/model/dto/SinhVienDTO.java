package com.luanvan.userservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienDTO {
    private String maSo;
    private String khoaHoc;
}

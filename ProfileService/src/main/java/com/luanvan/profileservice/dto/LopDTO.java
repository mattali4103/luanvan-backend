package com.luanvan.profileservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    NganhDTO nganhDTO;
    String chuNhiem;
}

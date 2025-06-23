package com.luanvan.profileservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSinhVienRequest {
    String maSo;
    String khoaHoc;
    String maLop;
}

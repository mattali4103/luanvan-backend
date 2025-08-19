package com.luanvan.profileservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GiaDinhResponse {
    String queQuan;
    String hoTenCha;
    String hoTenMe;
    String soDienThoaiNguoiThan;
}

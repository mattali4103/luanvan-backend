package com.luanvan.profileservice.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProfileResponse {
    String maSo;
    String khoaHoc;
    String tenNganh;
    String maLop;
    Long maSoThue;
    Long soTk;
    Long maKhoa;
}

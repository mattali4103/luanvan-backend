package com.luanvan.profileservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticsLopResponse {
    Long soLopHoc;
    Long tongSoSinhVien;

    public StatisticsLopResponse() {
        this.soLopHoc = 0L;
        this.tongSoSinhVien = 0L;
    }
}

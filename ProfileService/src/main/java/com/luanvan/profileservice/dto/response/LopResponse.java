package com.luanvan.profileservice.dto.response;

import com.luanvan.profileservice.dto.NganhDTO;
import com.luanvan.profileservice.dto.SinhVienDTO;
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
public class LopResponse {
    String maLop;
    String tenLop;
    String chuNhiem;
    Long siSo;
    Long siSoCon;
}

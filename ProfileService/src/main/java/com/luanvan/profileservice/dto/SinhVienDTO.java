package com.luanvan.profileservice.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienDTO {
    String maSo;
    String khoaHoc;
    String maLop;
    LopDTO lopDTO;
}

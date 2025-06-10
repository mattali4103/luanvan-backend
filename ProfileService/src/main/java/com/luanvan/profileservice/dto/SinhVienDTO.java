package com.luanvan.profileservice.dto;


import lombok.*;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
@Getter
@Setter
@Builder
public class SinhVienDTO {
    private String maSo;
    private String khoaHoc;
    private Long maNganh;
}

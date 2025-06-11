package com.luanvan.ketquahoctapservice.model.dto;

import lombok.*;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
@Getter
@Setter
@Builder
public class SinhvienDTO {
    private String maSo;
    private String khoaHoc;
}
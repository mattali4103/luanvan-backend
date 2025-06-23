package com.luanvan.profileservice.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddNganhToKhoaRequest {
    private Long maKhoa;
    private Long maNganh;
}

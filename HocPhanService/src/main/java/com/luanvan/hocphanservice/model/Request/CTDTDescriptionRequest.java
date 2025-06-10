package com.luanvan.hocphanservice.model.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CTDTDescriptionRequest {
    Long id;
    Long maNganh;
    String noiDung;
    String khoaHoc;
}

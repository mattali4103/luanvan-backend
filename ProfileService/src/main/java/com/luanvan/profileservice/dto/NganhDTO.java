package com.luanvan.profileservice.dto;

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
public class NganhDTO {
    Long maNganh;
    String tenNganh;
    KhoaDTO khoa;
}

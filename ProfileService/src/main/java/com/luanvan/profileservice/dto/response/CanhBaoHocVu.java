package com.luanvan.profileservice.dto.response;

import lombok.*;

import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CanhBaoHocVu {
    String maSo;
    String lyDo;
}

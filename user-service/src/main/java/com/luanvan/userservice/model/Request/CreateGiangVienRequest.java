package com.luanvan.userservice.model.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateGiangVienRequest {
    String maCb;
    Long ma_so_thue;
    Long so_tk;
    Long ma_khoa;
}

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
public class CreateAdminRequest {
    String maCb;
    String ma_so_thue;
    String so_tk;
}

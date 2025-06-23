package com.luanvan.userservice.model.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAdminResponse {
    String maCb;
    String ma_so_thue;
    String so_tk;
}

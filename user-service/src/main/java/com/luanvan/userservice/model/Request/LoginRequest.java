package com.luanvan.userservice.model.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
    private String maSo;
    private String password;
}

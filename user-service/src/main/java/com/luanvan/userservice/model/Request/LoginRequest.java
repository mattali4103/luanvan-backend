package com.luanvan.userservice.model.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
    private String ma_so;
    private String password;
}

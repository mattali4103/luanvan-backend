package com.luanvan.learningprogress.model.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;
    private boolean isAuthenticated;
    private String identifier;
}

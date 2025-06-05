package com.luanvan.userservice.controller;


import com.luanvan.userservice.model.Request.LoginRequest;
import com.luanvan.userservice.model.Response.LoginResponse;
import com.luanvan.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request ) {
        var response = authenticationService.authenticate(request);
        return LoginResponse.builder()
                .token(response.getToken())
                .isAuthenticated(response.isAuthenticated())
                .build();
    }
}

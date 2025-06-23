package com.luanvan.userservice.controller;


import com.luanvan.userservice.model.Request.AuthenticationRequest;
import com.luanvan.userservice.model.Request.LoginRequest;
import com.luanvan.userservice.model.Response.ApiResponse;
import com.luanvan.userservice.model.Response.AuthenticationResponse;
import com.luanvan.userservice.model.Response.LoginResponse;
import com.luanvan.userservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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

    @PostMapping("/validate")
    ApiResponse<AuthenticationResponse> validateToken(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .data(authenticationService.validateToken(request.getToken()))
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request.getToken());
        return ApiResponse.<AuthenticationResponse>builder().code(200)
                .data(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody AuthenticationRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request.getToken());
        return ApiResponse.<Void>builder().build();
    }
}

package com.luanvan.userservice.controller;

import com.luanvan.userservice.model.Request.CreateSinhVienRequest;
import com.luanvan.userservice.model.Response.ApiResponse;
import com.luanvan.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ApiResponse<Object> register(@RequestBody CreateSinhVienRequest request) {
        return ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(userService.create(request))
                .build();
    }
}

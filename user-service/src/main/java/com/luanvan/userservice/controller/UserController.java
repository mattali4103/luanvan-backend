package com.luanvan.userservice.controller;
import com.luanvan.userservice.model.Request.CreateSinhVienRequest;
import com.luanvan.userservice.model.Response.ApiResponse;
import com.luanvan.userservice.model.dto.UserDTO;
import com.luanvan.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/info/{maSo}")
    public ApiResponse<UserDTO> getUserByMaSo(@PathVariable String maSo){
        return ApiResponse.<UserDTO>builder()
                .code(200)
                .message("OK")
                .data(userService.getUserByMaSo(maSo))
                .build();
    }

    @PostMapping("register")
    public ApiResponse<Object> register(@RequestBody CreateSinhVienRequest request) {
        return ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(userService.create(request))
                .build();
    }
// PRIVATE API
    @RequestMapping("/id/{maSo}")
    public UserDTO getUserInfoByMaSo(@PathVariable String maSo){
        return userService.getUserByMaSo(maSo);
    }
}

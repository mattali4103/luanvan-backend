package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.SinhvienDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.services.SinhVienService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final SinhVienService sinhVienService;

    public ProfileController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }



    @PostMapping("sinh_vien/create")
    public ApiResponse<SinhvienDTO> createSinhVien(@RequestBody SinhvienDTO sv){
        return ApiResponse.<SinhvienDTO>builder()
                .code(200)
                .message("OK")
                .data(sinhVienService.createSinhVien(sv))
                .build();
    }
}

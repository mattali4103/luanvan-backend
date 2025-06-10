package com.luanvan.profileservice.controller;


import com.luanvan.profileservice.dto.DiemDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;

import com.luanvan.profileservice.services.DiemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile/diem")
public class DiemController {
    private final DiemService diemService;

    public DiemController(DiemService diemService) {
        this.diemService = diemService;
    }

    @GetMapping("/getByMaSo/{maSo}")
    public ApiResponse<List<DiemDTO>> getByMaSo(@PathVariable  String maSo) {
        return ApiResponse.<List<DiemDTO>>builder()
                .code(200)
                .message("Diem retrieved successfully")
                .data(diemService.getDiemByMaSo(maSo))
                .build();
    }

    @GetMapping("/getByMaHocKy/{maHocKy}")
    public ApiResponse<DiemDTO> getByMaHocKy(@PathVariable  Long maHocKy) {
        return ApiResponse.<DiemDTO>builder()
                .code(200)
                .message("Diem retrieved successfully")
                .data(diemService.getDiemByMaHocKy(maHocKy))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<DiemDTO> createDiem(DiemDTO diemDTO) {
        return ApiResponse.<DiemDTO>builder()
                .code(200)
                .message("Diem created successfully")
                .data(diemService.createDiem(diemDTO))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<DiemDTO> updateDiem(@RequestBody DiemDTO diemDTO) {
        return ApiResponse.<DiemDTO>builder()
                .code(200)
                .message("Diem updated successfully")
                .data(diemService.updateDiem(diemDTO))
                .build();
    }

}

package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.KhoaDTO;
import com.luanvan.profileservice.dto.request.AddNganhToKhoaRequest;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.services.KhoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/khoa")
@RequiredArgsConstructor
public class KhoaController {
    private final KhoaService khoaService;

    @PostMapping("/create")
    public ApiResponse<KhoaDTO> createKhoa(@RequestBody KhoaDTO khoaDTO) {
        return ApiResponse.<KhoaDTO>builder()
                .code(200)
                .message("OK")
                .data(khoaService.createKhoa(khoaDTO))
                .build();
    }

    @PostMapping("/add-nganh-to-khoa")
    public ApiResponse<KhoaDTO> addNganhToKhoa(@RequestBody AddNganhToKhoaRequest request) {
        return ApiResponse.<KhoaDTO>builder()
                .code(200)
                .message("OK")
                .data(khoaService.addNganhToKhoa(request.getMaKhoa(), request.getMaNganh()))
                .build();
    }

    @GetMapping("/id/{maKhoa}")
    public ApiResponse<KhoaDTO> getKhoaByMaKhoa(@PathVariable Long maKhoa) {
        return ApiResponse.<KhoaDTO>builder()
                .code(200)
                .message("OK")
                .data(khoaService.getKhoaByMaKhoa(maKhoa))
                .build();
    }

    @PutMapping("/update/{maKhoa}")
    public ApiResponse<KhoaDTO> updateKhoa(@PathVariable Long maKhoa, @RequestBody KhoaDTO khoaDTO) {
        return ApiResponse.<KhoaDTO>builder()
                .code(200)
                .message("OK")
                .data(khoaService.updateKhoa(maKhoa, khoaDTO))
                .build();
    }

    @DeleteMapping("/delete/{maKhoa}")
    public ApiResponse<Void> deleteKhoa(@PathVariable Long maKhoa) {
        khoaService.deleteKhoa(maKhoa);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("OK")
                .build();
    }
}

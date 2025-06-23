package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.NganhDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.services.NganhService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/nganh")
@RequiredArgsConstructor
public class NganhController {
    private final NganhService nganhService;

    @PostMapping("/create")
    public ApiResponse<NganhDTO> createNganh(@RequestBody NganhDTO nganhDTO) {
        return ApiResponse.<NganhDTO>builder()
                .code(200)
                .message("OK")
                .data(nganhService.createNganh(nganhDTO))
                .build();
    }

    @GetMapping("/id/{maNganh}/exist")
    public boolean existByMaNganh(@PathVariable Long maNganh) {
            return nganhService.existByMaNganh(maNganh);
    }

    @GetMapping("/id/{maNganh}")
    public ApiResponse<NganhDTO> getNganhByMaNganh(@PathVariable Long maNganh) {
        return ApiResponse.<NganhDTO>builder()
                .code(200)
                .message("OK")
                .data(nganhService.getNganhByMaNganh(maNganh))
                .build();
    }

    @PutMapping("/update/{maNganh}")
    public ApiResponse<NganhDTO> updateNganh( @RequestBody NganhDTO nganhDTO) {
        return ApiResponse.<NganhDTO>builder()
                .code(200)
                .message("OK")
                .data(nganhService.updateNganh(nganhDTO))
                .build();
    }

    @DeleteMapping("/delete/{maNganh}")
    public ApiResponse<Void> deleteNganh(@PathVariable Long maNganh) {
        nganhService.deleteNganh(maNganh);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("OK")
                .build();
    }
}


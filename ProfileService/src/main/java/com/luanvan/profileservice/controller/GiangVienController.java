package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.GiangVienDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.services.GiangVienService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/giangvien")
public class GiangVienController {
    private final GiangVienService giangVienService;

    public GiangVienController(GiangVienService giangVienService) {
        this.giangVienService = giangVienService;
    }

    @PostMapping("/create")
    public ApiResponse<GiangVienDTO> createGiangVien(@RequestBody GiangVienDTO gv) {
        return ApiResponse.<GiangVienDTO>builder()
                .code(200)
                .message("OK")
                .data(giangVienService.createGiangVien(gv))
                .build();
    }

    @GetMapping("/id/{maSo}")
    public GiangVienDTO getGiangVienByMaSo(@PathVariable String maSo) {
        return giangVienService.findById(maSo);
    }

    @PutMapping("/update")
    public ApiResponse<GiangVienDTO> updateGiangVien(@RequestBody GiangVienDTO gv) {
        return ApiResponse.<GiangVienDTO>builder()
                .code(200)
                .message("OK")
                .data(giangVienService.updateGiangVien(gv))
                .build();
    }

    @DeleteMapping("/delete/{maSo}")
    public ApiResponse<Void> deleteGiangVien(@PathVariable String maSo) {
        giangVienService.deleteGiangVien(maSo);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("OK")
                .build();
    }
}

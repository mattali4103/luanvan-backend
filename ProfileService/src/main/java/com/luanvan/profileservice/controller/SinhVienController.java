package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.SinhVienDTO;
import com.luanvan.profileservice.dto.request.CreateSinhVienRequest;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.dto.response.SinhVienPreviewProfile;
import com.luanvan.profileservice.dto.response.ThongKeKetQuaSinhVien;
import com.luanvan.profileservice.services.SinhVienService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/sinhvien")
public class SinhVienController {

    private final SinhVienService sinhVienService;

    public SinhVienController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    @PostMapping("/create")
    public ApiResponse<SinhVienDTO> createSinhVien(@RequestBody CreateSinhVienRequest sv) {
        return ApiResponse.<SinhVienDTO>builder()
                .code(200)
                .message("OK")
                .data(sinhVienService.createSinhVien(sv))
                .build();
    }
    @GetMapping("/id/{maSo}")
    public ApiResponse<SinhVienDTO> getSinhVienByMaSo(@PathVariable String maSo) {
        return ApiResponse.<SinhVienDTO>builder()
                .code(200)
                .message("OK")
                .data(sinhVienService.findById(maSo))
                .build();
    }

    @GetMapping("/me/{maSo}")
    public ApiResponse<ProfileResponse> getMyInfo(@PathVariable String maSo) {
        return ApiResponse.<ProfileResponse>builder()
                .code(200)
                .message("OK")
                .data(sinhVienService.getMyInfo(maSo))
                .build();
    }


    @PutMapping("/update")
    public ApiResponse<SinhVienDTO> updateSinhVien(@RequestBody SinhVienDTO sv) {
        return ApiResponse.<SinhVienDTO>builder()
                .code(200)
                .message("OK")
                .data(sinhVienService.updateSinhVien(sv))
                .build();
    }

    @DeleteMapping("/delete/{maSo}")
    public ApiResponse<Void> deleteSinhVien(@PathVariable String maSo) {
        sinhVienService.deleteSinhVien(maSo);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("OK")
                .build();
    }
    @GetMapping("/thongke/{maSo}")
    public ApiResponse<SinhVienPreviewProfile> getThongKeByMaSo(@PathVariable String maSo) {
        return ApiResponse.<SinhVienPreviewProfile>builder()
                .code(200)
                .message("OK")
                .data(sinhVienService.getSinhVienPreviewProfile(maSo))
                .build();
    }
    //Lấy đầy đủ thông tin sinh viên bao gồm cả thông tin user
}

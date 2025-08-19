package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.LopDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.dto.response.SinhVienPreviewProfile;
import com.luanvan.profileservice.dto.response.StatisticsLopResponse;
import com.luanvan.profileservice.services.LopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile/lop")
public class LopController {
    private final LopService lopService;
    //Thong ke lop
    @GetMapping("/thongke")
    public ApiResponse<StatisticsLopResponse> getStatisticsLop(@RequestParam("maNganh") Long maNganh) {
        return ApiResponse.<StatisticsLopResponse>builder()
                .code(200)
                .message("OK")
                .data(lopService.getStatistics(maNganh))
                .build();
    }
    @GetMapping("/get_by_chu_nhiem/{maGiangVien}")
    public ApiResponse<List<LopDTO>> getLopByChuNhiem(@PathVariable String maGiangVien) {
        return ApiResponse.<List<LopDTO>>builder()
                .code(200)
                .message("OK")
                .data(lopService.getDSLopByChuNhiem(maGiangVien))
                .build();
    }
    @GetMapping("/thongke/chu_nhiem/{maGiangVien}")
    public ApiResponse<StatisticsLopResponse> getStatisticsLopByChuNhiem(@PathVariable String maGiangVien) {
        return ApiResponse.<StatisticsLopResponse>builder()
                .code(200)
                .message("OK")
                .data(lopService.getStatisticsByChuNhiem(maGiangVien))
                .build();
    }

    @GetMapping("/preview/{maLop}")
    public ApiResponse<List<SinhVienPreviewProfile>> getSinhVienPreviewProfileByMaLop(@PathVariable String maLop) {
        return ApiResponse.<List<SinhVienPreviewProfile>>builder()
                .code(200)
                .message("OK")
                .data(lopService.getPreviewProfileByMaLop(maLop))
                .build();
    }

    //   API PRIVATE
    @GetMapping("/get_by_ma_lop/{maLop}")
    public LopDTO getLopByMaLop(@PathVariable String maLop) {
        return lopService.getLopByMaLop(maLop);
    }
    @GetMapping("/get_sinhvien_profile_in_lop/{maLop}")
    public List<ProfileResponse> getSinhVienProfileInLop(@PathVariable String maLop) {
        return lopService.getSinhVienProfileByLop(maLop);
    }
    @GetMapping("/get_dslop_by_ma_khoa/{maKhoa}")
    public List<LopDTO> getDSLopByMaKhoa(@PathVariable String maKhoa) {
        return lopService.getDSLopByMaKhoa(maKhoa);
    }
}

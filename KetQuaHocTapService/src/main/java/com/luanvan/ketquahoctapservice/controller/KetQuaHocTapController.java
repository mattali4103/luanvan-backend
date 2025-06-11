package com.luanvan.ketquahoctapservice.controller;

import com.luanvan.ketquahoctapservice.model.Response.ApiResponse;
import com.luanvan.ketquahoctapservice.model.Response.DiemTrungBinh;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDTO;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDetail;
import com.luanvan.ketquahoctapservice.service.KetQuaHocTapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/kqht")
@RequiredArgsConstructor
public class KetQuaHocTapController {
    private final KetQuaHocTapService ketQuaHocTapService;


    @PostMapping("/import")
    public ApiResponse<Void> importKetQuaHocTap(@RequestParam("file") MultipartFile file) {
        ketQuaHocTapService.createKHHTByFileExcel(file);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("success")
                .build();
    }

    @GetMapping("/{maSo}")
    public ApiResponse<List<KetQuaHocTapDTO>> getKetQuaHocTap(@PathVariable String maSo) {
        return ApiResponse.<List<KetQuaHocTapDTO>>builder()
                .code(200)
                .message("OK")
                .data(null)
                .build();
    }
    @GetMapping("/detail/{maSo}")
    public ApiResponse<List<KetQuaHocTapDetail>> getKetQuaHocTapDetail(@PathVariable String maSo) {
        return ApiResponse.<List<KetQuaHocTapDetail>>builder()
                .build();
    }

    @PostMapping("/diem/trung_binh_hoc_ky/list")
    public ApiResponse<List<DiemTrungBinh>> getDiemTrungBinhListByMaSo(@RequestBody KetQuaHocTapDTO dto) {
        return ApiResponse.<List<DiemTrungBinh>>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.getDiemTrungBinhListByMaSo(dto.getMaSo()))
                .build();
    }

    @PostMapping("/diem/trung_binh_hoc_ky")
    public ApiResponse<Double> getDiemTrungBinhHocKy(@RequestBody KetQuaHocTapDTO ketQuaHocTapDTO) {
        return ApiResponse.<Double>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.getDiemTrungBinhHocKy(ketQuaHocTapDTO.getMaSo(), ketQuaHocTapDTO.getMaHocKy()))
                .build();
    }

}

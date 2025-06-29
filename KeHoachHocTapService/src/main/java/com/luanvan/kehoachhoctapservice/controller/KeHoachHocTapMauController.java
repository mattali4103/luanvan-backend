package com.luanvan.kehoachhoctapservice.controller;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTapMau;
import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import com.luanvan.kehoachhoctapservice.model.response.ApiResponse;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapDetail;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapGroup;
import com.luanvan.kehoachhoctapservice.service.KeHoachHocTapMauService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/khht/mau")
public class KeHoachHocTapMauController {

    private final KeHoachHocTapMauService keHoachHocTapMauService;

    public KeHoachHocTapMauController(KeHoachHocTapMauService keHoachHocTapMauService) {
        this.keHoachHocTapMauService = keHoachHocTapMauService;
    }

    @GetMapping("/by_ma_nganh")
    public ApiResponse<List<KeHoachHocTapDetail>> getKeHoachHocTapMauByKhoaHocAndMaNganh(
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("maNganh") Long maNganh) {
        List<KeHoachHocTapDetail> keHoachHocTapMauList = keHoachHocTapMauService.getKHHTMauByKhoaHocAndMaNganh(khoaHoc, maNganh);
        return ApiResponse.<List<KeHoachHocTapDetail>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapMauList)
                .build();
    }
    @GetMapping("/group_by_khoa_hoc")
    public ApiResponse<List<KeHoachHocTapGroup>> getKeHoachHocTapMauGroupByKhoaHoc(
            @RequestParam("maNganh") Long maNganh) {
        List<KeHoachHocTapGroup> keHoachHocTapMauList = keHoachHocTapMauService.getKeHoachHocTapByMaNganh(maNganh);
        return ApiResponse.<List<KeHoachHocTapGroup>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapMauList)
                .build();
    }

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public ApiResponse<Void> importKeHoachHocTapMau(
            @RequestParam("file") MultipartFile file,
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("maNganh") Long maNganh)
            {
        keHoachHocTapMauService.importKeHoachHocTapMau(khoaHoc, maNganh, file);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Import successful")
                .build();
    }

    @GetMapping("/detail")
    public ApiResponse<List<KeHoachHocTapDetail>> getKeHoachHocTapMau(
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("maNganh") Long maNganh,
            @RequestParam("maHocKy") Long maHocKy) {
        return ApiResponse.<List<KeHoachHocTapDetail>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapMauService.getKeHoachHocTapMau(khoaHoc, maNganh, maHocKy))
                .build();
    }

    @GetMapping("/hocky")
    public ApiResponse<List<HocKyDTO>> getHocKyInKHHTMau(
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("maNganh") Long maNganh) {
        return ApiResponse.<List<HocKyDTO>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapMauService.getHocKyInKHHTMau(khoaHoc, maNganh))
                .build();
    }
}

package com.luanvan.hocphanservice.controller;


import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.services.HocPhanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/hocphan")
public class HocPhanController {
    private final HocPhanService hocPhanService;

    public HocPhanController(HocPhanService hocPhanService) {
        this.hocPhanService = hocPhanService;
    }

    //    API doc file excel
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    ApiResponse<Void> saveFileData(@RequestParam("file") MultipartFile file) {
        hocPhanService.createDSHocPhanFromFile(file);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("success")
                .build();
    }


    @GetMapping("/id/{maHp}")
    public ApiResponse<HocPhanDTO> getHocPhanById(@PathVariable String maHp) {
        return ApiResponse.<HocPhanDTO>builder()
                .code(200)
                .message("success")
                .data(hocPhanService.getDsHocPhanById(maHp))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<HocPhanDTO> createHocPhan(@RequestBody HocPhanDTO hocPhanDTO) {
        return ApiResponse.<HocPhanDTO>builder()
                .code(200)
                .message("success")
                .data(hocPhanService.createHocPhan(hocPhanDTO))
                .build();
    }

    @PostMapping("/creates")
    public ApiResponse<List<HocPhanDTO>> createHocPhan(@RequestBody List<HocPhanDTO> DSHocPhanDTO) {
        return ApiResponse.<List<HocPhanDTO>>builder()
                .code(200)
                .message("success")
                .data(hocPhanService.createDSHocPhan(DSHocPhanDTO))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<List<HocPhanDTO>> getDsHocPhan() {
        return ApiResponse.<List<HocPhanDTO>>builder()
                .code(200)
                .message("success")
                .data(hocPhanService.getDsHocPhan())
                .build();
    }
}

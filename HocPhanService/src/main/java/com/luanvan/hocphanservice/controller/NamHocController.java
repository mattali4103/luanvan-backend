package com.luanvan.hocphanservice.controller;

import com.luanvan.hocphanservice.model.NamHocDTO;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.model.Response.NamHocResponse;
import com.luanvan.hocphanservice.services.NamHocService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/namhoc")
public class NamHocController {
    private final NamHocService namHocService;

    public NamHocController(NamHocService namHocService) {
        this.namHocService = namHocService;
    }

    @GetMapping("id/{id}")
    public ApiResponse<NamHocDTO> getNamHocById(@PathVariable  Long id) {
        return ApiResponse.<NamHocDTO>builder()
                .code(200)
                .message("success")
                .data(namHocService.getNamHocById(id))
                .build();
    }

    @PostMapping(value = "nam_hoc_in/list")
    List<NamHocResponse> getAllNamHoc(@RequestBody List<Long> namHocList){
        return namHocService.getNamHocByMaNamHocIn(namHocList);
    }


    @GetMapping("/list")
    public ApiResponse<List<NamHocDTO>> getAllNamHoc() {
        return ApiResponse.<List<NamHocDTO>>builder()
                .code(200)
                .message("success")
                .data(namHocService.getAllNamHoc())
                .build();
    }
    @PostMapping("/find")
    public ApiResponse<NamHocDTO> findByNamBatDauAndNamKetThuc(@RequestBody NamHocDTO namHocDTO) {
        return ApiResponse.<NamHocDTO>builder()
                .code(200)
                .message("success")
                .data(namHocService.findByNamBatDauAndNamKetThuc(namHocDTO.getNamBatDau(), namHocDTO.getNamKetThuc()))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<NamHocDTO> createNamHoc(@RequestBody NamHocDTO namHocDTO) {
        return ApiResponse.<NamHocDTO>builder()
                .code(200)
                .message("success")
                .data(namHocService.create(namHocDTO))
                .build();
    }
    @PutMapping("/update/{id}")
    public ApiResponse<NamHocDTO> updateNamHoc(@PathVariable Long id, @RequestBody NamHocDTO namHocDTO) {
        return ApiResponse.<NamHocDTO>builder()
                .code(200)
                .message("success")
                .data(namHocService.updateNamHoc(id, namHocDTO))
                .build();
    }
}

package com.luanvan.ketquahoctapservice.controller;

import com.luanvan.ketquahoctapservice.model.Response.ApiResponse;
import com.luanvan.ketquahoctapservice.model.Response.DiemTrungBinh;
import com.luanvan.ketquahoctapservice.model.Response.KetQuaHocTapByHocKy;
import com.luanvan.ketquahoctapservice.model.Response.PageResponse;
import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
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

    @SuppressWarnings("unused")
    @GetMapping("/{maSo}")
    public ApiResponse<List<KetQuaHocTapDTO>> getKetQuaHocTap(@PathVariable String maSo) {
        return ApiResponse.<List<KetQuaHocTapDTO>>builder()
                .code(200)
                .message("OK")
                .data(null)
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

    @GetMapping("/detail/page")
    public ApiResponse<PageResponse<KetQuaHocTapDetail>> getKetQuaHocTapDetailByMaSo(
            @RequestParam(value = "maSo") String maSo,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<KetQuaHocTapDetail>>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.getKetQuaHocTapDetailList(maSo, page, size))
                .build();
    }

    //   Lấy mã học kỳ theo mã số sinh viên
    @GetMapping("/hoc-ky/{maSo}")
    public ApiResponse<List<HocKyDTO>> getMaHocKyByMaSo(@PathVariable String maSo) {
        List<HocKyDTO> hocKyDTOList = ketQuaHocTapService.getMaHocKyByMaSo(maSo);
        return ApiResponse.<List<HocKyDTO>>builder()
                .code(200)
                .message("OK")
                .data(hocKyDTOList)
                .build();
    }

    @GetMapping("/detail")
    public ApiResponse<KetQuaHocTapByHocKy> getKetQuaHocTapByHocKy(
            @RequestParam(value = "maSo") String maSo,
            @RequestParam(value = "maHocKy", defaultValue = "1", required = false) Long maHocKy)
    {
        return ApiResponse.<KetQuaHocTapByHocKy>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.getKetQuaHocTapByHocKy(maSo, maHocKy))
                .build();
    }
//    Private API
    @GetMapping("private/hoc-phan-cai-thien/{maSo}")
    public ApiResponse<List<KetQuaHocTapDTO>> getHocPhanCanCaiThienByMaSo(@PathVariable String maSo) {
        return ApiResponse.<List<KetQuaHocTapDTO>>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.getHocPhanCaiThienByMaSo(maSo))
                .build();
    }
    @GetMapping("private/diem-chu-f/{maSo}")
    public ApiResponse<List<KetQuaHocTapDTO>> getKetQuaHocTapByDiemChuF(@PathVariable String maSo) {
        return ApiResponse.<List<KetQuaHocTapDTO>>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.getHocPhanFailed(maSo))
                .build();
    }
}

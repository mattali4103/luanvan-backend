package com.luanvan.hocphanservice.controller;

import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.HocPhanTuChonDTO;
import com.luanvan.hocphanservice.model.Request.HocPhanRequest;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.services.ChuongTrinhDaoTaoService;
import com.luanvan.hocphanservice.services.HocPhanTuChonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hocphan/tuchon")
public class HocPhanTuChonController {
    private final HocPhanTuChonService hocPhanTuChonService;


    public HocPhanTuChonController(HocPhanTuChonService hocPhanTuChonService, ChuongTrinhDaoTaoService chuongTrinhDaoTaoService) {
        this.hocPhanTuChonService = hocPhanTuChonService;

    }

    @PostMapping("/create")
    public ApiResponse<HocPhanTuChonDTO> create(@RequestBody HocPhanTuChonDTO dto) {
        return ApiResponse.<HocPhanTuChonDTO>builder()
                .code(200)
                .message("Created")
                .data(hocPhanTuChonService.create(dto))
                .build();
    }
    @PostMapping("/add/{id}")
    public ApiResponse<HocPhanTuChonDTO> add(@PathVariable Long id, @RequestBody List<HocPhanDTO> hocPhanDTOList) {
        return ApiResponse.<HocPhanTuChonDTO>builder()
                .code(200)
                .message("Added")
                .data(hocPhanTuChonService.addHocPhanTuChon(id, hocPhanDTOList))
                .build();
    }
    @GetMapping("/id/{id}")
    public ApiResponse<HocPhanTuChonDTO> getById(@PathVariable Long id) {
        return ApiResponse.<HocPhanTuChonDTO>builder()
                .code(200)
                .message("Retrieved")
                .data(hocPhanTuChonService.getHocPhanTuChon(id))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        hocPhanTuChonService.deleteHocPhanTuChon(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Deleted")
                .build();
    }
    @PostMapping("/by_loai_hp")
    public ApiResponse<HocPhanTuChonDTO> getHocPhanByKhoaHocAndTenNhom(@RequestBody HocPhanRequest request) {
        return ApiResponse.<HocPhanTuChonDTO>builder()
                .code(200)
                .message("OK")
                .data(hocPhanTuChonService.getHocPhanTuChonByNameAndKhoaHoc(request.getLoaiHp(), request.getKhoaHoc()))
                .build();
    }
    @GetMapping("/list")
    public ApiResponse<List<HocPhanTuChonDTO>> getAllByKhoaHoc(
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("maNganh") Long maNganh
    ) {
        return ApiResponse.<List<HocPhanTuChonDTO>>builder()
                .code(200)
                .message("OK")
                .data(hocPhanTuChonService.getAllByKhoaHocAndMaNganh(khoaHoc, maNganh))
                .build();
    }
}

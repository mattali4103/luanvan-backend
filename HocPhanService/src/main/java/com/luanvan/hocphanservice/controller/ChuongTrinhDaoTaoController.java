package com.luanvan.hocphanservice.controller;
import com.luanvan.hocphanservice.model.ChuongTrinhDaoTaoDTO;
import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.Request.CTDTDescriptionRequest;
import com.luanvan.hocphanservice.model.Request.HocPhanRequest;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.services.ChuongTrinhDaoTaoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/ctdt")
public class ChuongTrinhDaoTaoController {
    private final ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;

    public ChuongTrinhDaoTaoController(ChuongTrinhDaoTaoService chuongTrinhDaoTaoService) {
        this.chuongTrinhDaoTaoService = chuongTrinhDaoTaoService;
    }

    @GetMapping("/hoc_phan_not_in_ke_hoach")
    public ApiResponse<List<HocPhanDTO>> getHocPhanNotInKeHoachHocTap(@RequestBody HocPhanRequest request) {
        return ApiResponse.<List<HocPhanDTO>>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.findHocPhanNotInKeHoachHocTap(request.getMaSo(), request.getKhoaHoc()))
                .build();
    }

    @GetMapping("/id/{khoaHoc}")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getChuongTrinhDaoTaoByKhoaHoc(@PathVariable String khoaHoc) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getByKhoaHoc(khoaHoc))
                .build();
    }
    @GetMapping("/nganh/{maNganh}")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getChuongTrinhDaoTaoByNganh(@PathVariable Long maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getCTDTByMaNganh(maNganh))
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<ChuongTrinhDaoTaoDTO> createChuongTrinhDaoTao(@RequestBody ChuongTrinhDaoTaoDTO chuongTrinhDaoTaoDTO) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(201)
                .message("Created")
                .data(chuongTrinhDaoTaoService.create(chuongTrinhDaoTaoDTO))
                .build();
    }

    @PostMapping("/create/upload")
    public ApiResponse<ChuongTrinhDaoTaoDTO> createChuongTrinhDaoTaoFromFile(
            @RequestParam("maNganh") Long maNganh,
            @RequestParam("noiDung") String noiDung,
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("file") MultipartFile file) {
        CTDTDescriptionRequest request = CTDTDescriptionRequest.builder()
                .maNganh(maNganh)
                .noiDung(noiDung)
                .khoaHoc(khoaHoc)
                .build();
        chuongTrinhDaoTaoService.createDSHocPhanFromFile(request,file);
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(201)
                .message("Created OK")
                .build();
    }
    @PutMapping("/update")
    public ApiResponse<ChuongTrinhDaoTaoDTO> updateChuongTrinhDaoTao(@RequestBody ChuongTrinhDaoTaoDTO chuongTrinhDaoTaoDTO) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("Updated")
                .data(chuongTrinhDaoTaoService.update(chuongTrinhDaoTaoDTO))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteChuongTrinhDaoTao(@PathVariable String id) {
        chuongTrinhDaoTaoService.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(204)
                .message("Deleted")
                .build();
    }
}

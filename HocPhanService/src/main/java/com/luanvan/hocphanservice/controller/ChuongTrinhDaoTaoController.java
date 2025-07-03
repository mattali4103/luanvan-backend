package com.luanvan.hocphanservice.controller;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import com.luanvan.hocphanservice.model.ChuongTrinhDaoTaoDTO;
import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.Request.CTDTDescriptionRequest;
import com.luanvan.hocphanservice.model.Request.HocPhanRequest;
import com.luanvan.hocphanservice.model.Request.KeHoachHocTapRequest;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.model.Response.ThongKeCTDT;
import com.luanvan.hocphanservice.model.Response.TinChiResponse;
import com.luanvan.hocphanservice.services.ChuongTrinhDaoTaoService;
import com.luanvan.hocphanservice.services.HocPhanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/ctdt")
public class ChuongTrinhDaoTaoController {
    private final ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;
    private final HocPhanService hocPhanService;

    public ChuongTrinhDaoTaoController(ChuongTrinhDaoTaoService chuongTrinhDaoTaoService, HocPhanService hocPhanService) {
        this.chuongTrinhDaoTaoService = chuongTrinhDaoTaoService;
        this.hocPhanService = hocPhanService;
    }

    @GetMapping("/thongke")
    public ApiResponse<ThongKeCTDT> getThongKeCTDT(
            @RequestParam("id") Long id
    ) {
        return ApiResponse.<ThongKeCTDT>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getThongKeCTDT(id))
                .build();
    }

    @GetMapping("/get_by_ma_nganh/{maNganh}")
    public ApiResponse<List<ChuongTrinhDaoTaoDTO>> getCTDTByMaNganh(
            @PathVariable Long maNganh) {
        return ApiResponse.<List<ChuongTrinhDaoTaoDTO>>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getCTDTByMaNganh(maNganh))
                .build();
    }


    @PostMapping("/hoc_phan_not_in/{khoaHoc}/{maNganh}")
    List<HocPhanDTO> getHocPhanNotInCTDT(@PathVariable String khoaHoc, @PathVariable Long maNganh, @RequestBody List<String> hocPhanList) {
        return chuongTrinhDaoTaoService.getHocPhanNotInCTDT(hocPhanList, khoaHoc, maNganh);
    }

    @GetMapping("/khoahoc/{khoaHoc}/{maNganh}")
    public ApiResponse<List<HocPhanDTO>> getHocPhanByKhoaHoc(@PathVariable String khoaHoc, @PathVariable Long maNganh) {
        return ApiResponse.<List<HocPhanDTO>>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getHocPhanInCTDTByKhoaHoc(khoaHoc, maNganh))
                .build();

    }

    @GetMapping("/id/{khoaHoc}/{maNganh}")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getChuongTrinhDaoTaoByKhoaHoc(@PathVariable String khoaHoc, @PathVariable Long maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getByKhoaHocAndMaNganh(khoaHoc, maNganh))
                .build();
    }

    @GetMapping("/get/{khoaHoc}/{maNganh}")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getChuongTrinhDaoTaoByNganh(@PathVariable String khoaHoc, @PathVariable Long maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getCTDT(khoaHoc, maNganh))
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
        chuongTrinhDaoTaoService.createDSHocPhanFromFile(request, file);
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
    public ApiResponse<Void> deleteChuongTrinhDaoTao(@PathVariable Long id) {
        chuongTrinhDaoTaoService.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(204)
                .message("Deleted")
                .build();
    }

    // API FOR ADMIN
    @GetMapping("")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getCTDTByKhoaHocAndMaNganh(
            @RequestParam String khoaHoc,
            @RequestParam Long maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getByKhoaHocAndMaNganh(khoaHoc, maNganh))
                .build();
    }


    //    API for Service
    @PostMapping("/count/tinchi/{khoaHoc}/{maNganh}")
    public TinChiResponse getTongTinchi(@PathVariable String khoaHoc, @PathVariable Long maNganh, @RequestBody List<KeHoachHocTapRequest> request) {
        return chuongTrinhDaoTaoService.getCountTinChiByCTDT(khoaHoc, maNganh, request);
    }

    @PostMapping("/hocphan/by_loai_hp")
    public List<HocPhanDTO> getHocPhanInCTDTByLoaiHp(@RequestBody HocPhanRequest request) {
        return hocPhanService.getHocPhanInCTDTByLoaiHp(request.getLoaiHp(), request.getKhoaHoc(), request.getMaNganh());
    }
}

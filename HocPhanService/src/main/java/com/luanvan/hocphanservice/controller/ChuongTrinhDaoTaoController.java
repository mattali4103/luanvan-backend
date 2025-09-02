package com.luanvan.hocphanservice.controller;
import com.luanvan.hocphanservice.model.Request.ChuongTrinhDaoTaoRequest;
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
    public ApiResponse<List<ChuongTrinhDaoTaoRequest>> getCTDTByMaNganh(
            @PathVariable Long maNganh) {
        return ApiResponse.<List<ChuongTrinhDaoTaoRequest>>builder()
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
    public ApiResponse<ChuongTrinhDaoTaoRequest> getChuongTrinhDaoTaoByKhoaHoc(@PathVariable String khoaHoc, @PathVariable Long maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getByKhoaHocAndMaNganh(khoaHoc, maNganh))
                .build();
    }

    @GetMapping("/get/{maNganh}/{khoaHoc}")
    public ApiResponse<ChuongTrinhDaoTaoRequest> getChuongTrinhDaoTaoByNganh(@PathVariable Long maNganh, @PathVariable String khoaHoc) {
        ChuongTrinhDaoTaoRequest response = chuongTrinhDaoTaoService.getCTDT(khoaHoc, maNganh);
        if(response.getId() == null) {
            return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                    .code(200)
                    .message("Chương trình đào tạo không tồn tại")
                    .data(null)
                    .build();
        }
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(200)
                .message("OK")
                .data(response)
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<ChuongTrinhDaoTaoRequest> createChuongTrinhDaoTao(@RequestBody ChuongTrinhDaoTaoRequest chuongTrinhDaoTaoRequest) {
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(201)
                .message("Created")
                .data(chuongTrinhDaoTaoService.create(chuongTrinhDaoTaoRequest))
                .build();
    }

    @PostMapping("/create/upload")
    public ApiResponse<ChuongTrinhDaoTaoRequest> createChuongTrinhDaoTaoFromFile(
            @RequestParam("ten") String ten,
            @RequestParam("maNganh") Long maNganh,
            @RequestParam("noiDung") String noiDung,
            @RequestParam("khoaHoc") String khoaHoc,
            @RequestParam("file") MultipartFile file) {
        CTDTDescriptionRequest request = CTDTDescriptionRequest.builder()
                .tenChuongTrinhDaoTao(ten)
                .maNganh(maNganh)
                .noiDung(noiDung)
                .khoaHoc(khoaHoc)
                .build();
        chuongTrinhDaoTaoService.createDSHocPhanFromFile(request, file);
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(200)
                .message("Created OK")
                .build();
    }


    @PutMapping("/update")
    public ApiResponse<ChuongTrinhDaoTaoRequest> updateChuongTrinhDaoTao(@RequestBody ChuongTrinhDaoTaoRequest chuongTrinhDaoTaoRequest) {
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(200)
                .message("Updated")
                .data(chuongTrinhDaoTaoService.update(chuongTrinhDaoTaoRequest))
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
    @DeleteMapping("/delete/hocphan")
    public ApiResponse<Void> deleteHocPhanInCTDT(@RequestBody ChuongTrinhDaoTaoRequest request) {
        chuongTrinhDaoTaoService.deleteHocPhanInCTDT(request);
        return ApiResponse.<Void>builder()
                .code(204)
                .message("Deleted")
                .build();
    }
    @DeleteMapping("/delete/hoc_phan_tu_chon")
    public ApiResponse<Void> deleteHocPhanTuChonInCTDT(@RequestBody ChuongTrinhDaoTaoRequest request) {
        chuongTrinhDaoTaoService.deleteHocPhanTuChonInCTDT(request);
        return ApiResponse.<Void>builder()
                .code(204)
                .message("Deleted")
                .build();
    }
    @DeleteMapping("/delete/hoc_phan_in_hptc")
    public ApiResponse<Void> deleteHocPhanInHPTC(@RequestBody ChuongTrinhDaoTaoRequest request) {
        chuongTrinhDaoTaoService.deleteHocPhanInHocPhanTuChon(request);
        return ApiResponse.<Void>builder()
                .code(204)
                .message("Deleted")
                .build();
    }

    // API FOR ADMIN
    @GetMapping("")
    public ApiResponse<ChuongTrinhDaoTaoRequest> getCTDTByKhoaHocAndMaNganh(
            @RequestParam String khoaHoc,
            @RequestParam Long maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getByKhoaHocAndMaNganh(khoaHoc, maNganh))
                .build();
    }
    @PostMapping("/add_hoc_phan_tu_chon")
    public ApiResponse<ChuongTrinhDaoTaoRequest> addHocPhanTuChonInCTDT(@RequestBody ChuongTrinhDaoTaoRequest request) {
        return ApiResponse.<ChuongTrinhDaoTaoRequest>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.addHocPhanTuChon(request))
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

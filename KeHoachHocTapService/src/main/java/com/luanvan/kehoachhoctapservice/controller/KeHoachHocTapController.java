package com.luanvan.kehoachhoctapservice.controller;

import com.luanvan.kehoachhoctapservice.model.dto.HocPhanDTO;
import com.luanvan.kehoachhoctapservice.model.dto.KeHoachHocTapDTO;
import com.luanvan.kehoachhoctapservice.model.request.AddKHHTRequest;
import com.luanvan.kehoachhoctapservice.model.request.HocPhanRequest;
import com.luanvan.kehoachhoctapservice.model.request.KeHoachHocTapRequest;
import com.luanvan.kehoachhoctapservice.model.response.ApiResponse;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapDetail;
import com.luanvan.kehoachhoctapservice.model.response.TinChiResponse;
import com.luanvan.kehoachhoctapservice.service.KeHoachHocTapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khht")
public class KeHoachHocTapController {
    private final KeHoachHocTapService keHoachHocTapService;

    public KeHoachHocTapController(KeHoachHocTapService keHoachHocTapService) {
        this.keHoachHocTapService = keHoachHocTapService;
    }

    @GetMapping("/sinhvien/hoc_phan_not_in_khht/{maSo}/{khoaHoc}")
    public ApiResponse<List<HocPhanDTO>> getHocPhanNotInKHHTByKhoaHoc(@PathVariable String maSo, @PathVariable String khoaHoc) {
        return ApiResponse.<List<HocPhanDTO>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapService.getHocPhanNotInCTDT(maSo,khoaHoc))
                .build();
    }

    @GetMapping("/sinhvien/hoc_phan_in_ke_hoach_by_ma_so/{maSo}")
    public List<String> getMaHocPhanByMaSo(@PathVariable String maSo){
        return  keHoachHocTapService.getMaHocPhanByMaSo(maSo);
    }
    @PostMapping("/sinhvien/hocphan/by_loai_hp")
    public ApiResponse<List<KeHoachHocTapDetail>> getHocPhanInKHHTByLoaiHp(@RequestBody HocPhanRequest request) {
        return ApiResponse.<List<KeHoachHocTapDetail>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapService.getKHHTDetailByLoaiHP(request.getMaSo(),request.getKhoaHoc(),request.getLoaiHp()))
                .build();
    }

//    @GetMapping("/sinhvien/tinchi/count/hocky/")
//    public ApiResponse<KeHoachHocTapCountResponse> countKeHoachHocTapByMaSoAndMaHocKy(@RequestBody KeHoachHocTapRequest request) {
//        return ApiResponse.<KeHoachHocTapCountResponse>builder()
//                .code(200)
//                .message("OK")
//                .data(keHoachHocTapService.countKeHoachHocTapByMaSoAndMaHocKy(request))
//                .build();
//    }




    @GetMapping("/sinhvien/tinchi/count/{khoaHoc}/{maSo}")
    public ApiResponse<TinChiResponse> countKeHoachHocTapsByMaSo(@PathVariable String khoaHoc, @PathVariable String maSo) {
        return ApiResponse.<TinChiResponse>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapService.countKeHoachHocTapsByMaSo(maSo,khoaHoc))
                .build();
    }
    @GetMapping("/sinhvien/detail/{maSo}")
    public ApiResponse<List<KeHoachHocTapDetail>> getKhhtDetail(@PathVariable String maSo) {
        return ApiResponse.<List<KeHoachHocTapDetail>>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapService.getKHHTDetail(maSo))
                .build();
    }




    @GetMapping("/sinhvien/{maSo}")
    public ApiResponse<List<KeHoachHocTapDTO>> getKeHoachHocTapByMaSo(@PathVariable String maSo) {
        return ApiResponse.<List<KeHoachHocTapDTO>>builder()
                .code(200)
                .message("Lấy kế hoạch học tập thành công")
                .data(keHoachHocTapService.getKeHoachHocTapsByMaSo(maSo))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<KeHoachHocTapDTO> update(@RequestBody KeHoachHocTapDTO khht) {
        return ApiResponse.<KeHoachHocTapDTO>builder()
                .code(200)
                .message("Cập nhật kế hoạch học tập thành công")
                .data(keHoachHocTapService.update(khht))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<KeHoachHocTapDTO> create(@RequestBody AddKHHTRequest request) {
        return ApiResponse.<KeHoachHocTapDTO>builder()
                .code(200)
                .message("Tạo kế hoạch học tập thành công")
                .data(keHoachHocTapService.create(request))
                .build();
    }

    @PostMapping("/creates")
    public ApiResponse<Void> creates(@RequestBody List<KeHoachHocTapRequest> request) {
        keHoachHocTapService.creates(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("OK")
                .build();
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        keHoachHocTapService.delete(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Xóa kế hoạch học tập thành công")
                .build();
    }

}

package com.luanvan.kehoachhoctapservice.controller;

import com.luanvan.kehoachhoctapservice.model.KeHoachHocTapDTO;
import com.luanvan.kehoachhoctapservice.model.request.AddKHHTRequest;
import com.luanvan.kehoachhoctapservice.model.request.KeHoachHocTapRequest;
import com.luanvan.kehoachhoctapservice.model.response.ApiResponse;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapCountResponse;
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

    @GetMapping("/sinhvien/hoc_phan_in_ke_hoach_by_ma_so/{maSo}")
    public List<String> getMaHocPhanByMaSo(@PathVariable String maSo){
        return  keHoachHocTapService.getMaHocPhanByMaSo(maSo);
    }

    @GetMapping("/sinhvien/hocky/count")
    public ApiResponse<KeHoachHocTapCountResponse> countKeHoachHocTapByMaSoAndMaHocKy(@RequestBody KeHoachHocTapRequest request) {
        return ApiResponse.<KeHoachHocTapCountResponse>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapService.countKeHoachHocTapByMaSoAndMaHocKy(request))
                .build();
    }



    @GetMapping("/sinhvien/count")
    public ApiResponse<Long> countKeHoachHocTapsByMaSo(@RequestBody KeHoachHocTapRequest request) {
        return ApiResponse.<Long>builder()
                .code(200)
                .message("OK")
                .data(keHoachHocTapService.countKeHoachHocTapsByMaSo(request))
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


    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        keHoachHocTapService.delete(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Xóa kế hoạch học tập thành công")
                .build();
    }

}

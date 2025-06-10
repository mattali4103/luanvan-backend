package com.luanvan.ketquahoctapservice.controller;

import com.luanvan.ketquahoctapservice.model.Response.ApiResponse;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDTO;
import com.luanvan.ketquahoctapservice.service.KetQuaHocTapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kqht")
@RequiredArgsConstructor
public class KetQuaHocTapController {
    private final KetQuaHocTapService ketQuaHocTapService;

    @GetMapping("/{maSo}")
    public ApiResponse<List<KetQuaHocTapDTO>> getKetQuaHocTap(@PathVariable String maSo){
        return ApiResponse.<List<KetQuaHocTapDTO>>builder()
                .code(200)
                .message("OK")
                .data(null)
                .build();

    }
    @GetMapping("/test/{maSo}")
    public ApiResponse<Object> test(@PathVariable String maSo) {
        return ApiResponse.<Object>builder()
                .code(200)
                .message("OK")
                .data(ketQuaHocTapService.findBySinhVienId(maSo))
                .build();
    }
}

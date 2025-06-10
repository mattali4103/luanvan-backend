package com.luanvan.hocphanservice.controller;


import com.luanvan.hocphanservice.model.ChuongTrinhDaoTaoDTO;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.services.ChuongTrinhDaoTaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ctdt")
public class ChuongTrinhDaoTaoController {
    private final ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;

    public ChuongTrinhDaoTaoController(ChuongTrinhDaoTaoService chuongTrinhDaoTaoService) {
        this.chuongTrinhDaoTaoService = chuongTrinhDaoTaoService;
    }

    @GetMapping("/id/{maHp}")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getChuongTrinhDaoTaoById(@PathVariable String maHp) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(chuongTrinhDaoTaoService.getById(maHp))
                .build();
    }
    @GetMapping("/nganh/{maNganh}")
    public ApiResponse<ChuongTrinhDaoTaoDTO> getChuongTrinhDaoTaoByNganh(@PathVariable String maNganh) {
        return ApiResponse.<ChuongTrinhDaoTaoDTO>builder()
                .code(200)
                .message("OK")
                .data(null)
                .build();
    }
}

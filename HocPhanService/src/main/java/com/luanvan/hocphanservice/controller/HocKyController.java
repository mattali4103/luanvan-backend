package com.luanvan.hocphanservice.controller;


import com.luanvan.hocphanservice.model.HocKyDTO;
import com.luanvan.hocphanservice.model.Request.HocKyRequest;
import com.luanvan.hocphanservice.model.Response.ApiResponse;
import com.luanvan.hocphanservice.model.Response.HocKyResponse;
import com.luanvan.hocphanservice.services.HocKyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hocky")
@RequiredArgsConstructor
public class HocKyController {
    private final HocKyService hocKyService;

    @PostMapping("/create")
    public ResponseEntity<HocKyDTO> createHocKy(@RequestBody HocKyRequest hocKyRequest) {
        HocKyDTO hocKyDTO = hocKyService.create(hocKyRequest);
        return ResponseEntity.ok(hocKyDTO);
    }

    @PostMapping("/hoc_ky_in/list")
    public List<HocKyResponse> getHocKyIn(@RequestBody List<Long> hocKyList){
        return hocKyService.findHocKyIn(hocKyList);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<HocKyDTO> getHocKyById(@PathVariable("id") Long id) {
        HocKyDTO hocKyDTO = hocKyService.getHocKyById(id);
        return ResponseEntity.ok(hocKyDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HocKyDTO> updateHocKy(@PathVariable("id") Long id, @RequestBody HocKyDTO hocKyDTO) {
        HocKyDTO updated = hocKyService.updateHocKy(id, hocKyDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/list")
    public ApiResponse<List<HocKyDTO>> getAllHocKy() {
        List<HocKyDTO> list = hocKyService.getAllHocKy();
        return ApiResponse.<List<HocKyDTO>>builder()
                .code(200)
                .message("OK")
                .data(list)
                .build();
    }
}

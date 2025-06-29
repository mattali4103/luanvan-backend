package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.LopDTO;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.services.LopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile/lop")
public class LopController {
    private final LopService lopService;

    //   API PRIVATE
    @GetMapping("/get_by_ma_lop/{maLop}")
    public LopDTO getLopByMaLop(@PathVariable String maLop) {
        return lopService.getLopByMaLop(maLop);
    }
    @GetMapping("/get_sinhvien_profile_in_lop/{maLop}")
    public List<ProfileResponse> getSinhVienProfileInLop(@PathVariable String maLop) {
        return lopService.getSinhVienProfileByLop(maLop);
    }
    @GetMapping("/get_dslop_by_ma_khoa/{maKhoa}")
    public List<LopDTO> getDSLopByMaKhoa(@PathVariable String maKhoa) {
        return lopService.getDSLopByMaKhoa(maKhoa);
    }
}

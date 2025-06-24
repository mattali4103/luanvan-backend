package com.luanvan.kehoachhoctapservice.repository.httpClient;

import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import com.luanvan.kehoachhoctapservice.model.dto.HocPhanDTO;
import com.luanvan.kehoachhoctapservice.model.dto.NamHocDTO;
import com.luanvan.kehoachhoctapservice.model.request.HocPhanRequest;
import com.luanvan.kehoachhoctapservice.model.request.KeHoachHocTapRequest;
import com.luanvan.kehoachhoctapservice.model.response.TinChiResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name ="hoc-phan-service", url = "${hoc_phan_service_url}")
public interface HocPhanClient {
    @GetMapping(value ="/id/{maHocPhan}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean getHocPhanById(@PathVariable String maHocPhan);
    @PostMapping(value = "/hocphan/sinhvien/hoc_phan_in_khht", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HocPhanDTO> getHocPhanIn(@RequestBody List<String> hocPhanList);
    @PostMapping(value = "/hocky/hoc_ky_in/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HocKyDTO> getHocKyIn(@RequestBody List<Long> hocKyList);
    @PostMapping(value = "/nam_hoc_in/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NamHocDTO> getAllNamHoc(@RequestBody List<String> namHocList);
    @PostMapping("/ctdt/hoc_phan_not_in/{khoaHoc}")
    List<HocPhanDTO> getHocPhanNotInCTDT(@PathVariable String khoaHoc, @RequestBody List<String> hocPhanList);
    @GetMapping("/ctdt/count/tinchi/{khoaHoc}")
    TinChiResponse getCountTinChiByCTDT(@PathVariable String khoaHoc, @RequestBody List<KeHoachHocTapRequest> hocPhanList);
    @PostMapping("/ctdt/hocphan/by_loai_hp")
    List<HocPhanDTO> getHocPhanInCTDTByLoaiHp(@RequestBody HocPhanRequest request);
    @GetMapping("/hocky/find_by_name")
    HocKyDTO findHocKyByName(
            @RequestParam("namBatDau") String namBatDau,
            @RequestParam("namKetThuc") String namKetThuc,
            @RequestParam("tenHocKy") String tenHocKy
    );
}



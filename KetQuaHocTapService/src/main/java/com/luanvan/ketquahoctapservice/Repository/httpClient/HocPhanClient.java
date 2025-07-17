package com.luanvan.ketquahoctapservice.Repository.httpClient;

import com.luanvan.ketquahoctapservice.model.Response.TinChiResponse;
import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import com.luanvan.ketquahoctapservice.model.dto.HocPhanDTO;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDTO;
import com.luanvan.ketquahoctapservice.model.dto.NamHocDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    TinChiResponse getCountTinChiByCTDT(@PathVariable String khoaHoc, @RequestBody List<KetQuaHocTapDTO> hocPhanList);
    @PostMapping(value = "/namhoc/nam_hoc_in/list")
    List<NamHocDTO> getNamHocByMaNamHocIn(@RequestBody List<Long> namHocList);
    @GetMapping("/hocky/current")
    HocKyDTO getCurrentHocKy();
}

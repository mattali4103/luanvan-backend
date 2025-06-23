package com.luanvan.hocphanservice.repository.httpClient;

import com.luanvan.hocphanservice.model.Response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ke-hoach-hoc-tap-service", url = "${ke_hoach_hoc_tap_service_url}")
public interface KeHoachHocTapClient {
    @GetMapping(value ="sinhvien/hoc_phan_in_ke_hoach_by_ma_so/{maSo}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getHocPhanByMaSo(@PathVariable String maSo);
}

package com.luanvan.profileservice.repository.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "khht-client", url = "${khht_service_url}")
public interface KHHTClient {
    @GetMapping("/khht/statistic/sinhvien/count/hoc_ky_hien_tai/{maSo}")
    Long getCountTinChiDangKyByMaSo(@PathVariable String maSo);
}

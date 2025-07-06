package com.luanvan.profileservice.repository.httpClient;

import com.luanvan.profileservice.dto.response.ThongKeKetQuaSinhVien;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "kqht-client", url = "${kqht_service_url}")
public interface KQHTClient {
    @GetMapping("/kqht/private/thongke/{maSo}")
    ThongKeKetQuaSinhVien getThongKeByMaSo(@PathVariable String maSo);
}

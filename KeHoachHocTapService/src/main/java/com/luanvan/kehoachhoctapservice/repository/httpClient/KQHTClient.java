package com.luanvan.kehoachhoctapservice.repository.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "kqht-service", url = "${kqht_service_url}")
public interface KQHTClient {
    @GetMapping("/kqht/private/hoc-phan/{maSo}")
    List<String> getHocPhanByMaSo(@PathVariable String maSo);
}

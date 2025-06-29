package com.luanvan.kehoachhoctapservice.repository.httpClient;

import com.luanvan.kehoachhoctapservice.model.dto.LopDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${profile_service_url}")
public interface ProfileClient {
    @GetMapping("/lop/get_by_ma_lop/{maLop}")
    LopDTO getLopByMaLop(@PathVariable String maLop);
}

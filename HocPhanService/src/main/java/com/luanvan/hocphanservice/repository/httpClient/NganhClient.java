package com.luanvan.hocphanservice.repository.httpClient;

import com.luanvan.hocphanservice.model.Request.NganhDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${profile_service_url}")
public interface NganhClient {
    @GetMapping("/nganh/id/{maNganh}/exist")
    boolean existByMaNganh(@PathVariable Long maNganh);
    @GetMapping("/nganh/private/id/{maNganh}")
    NganhDTO findBymaNganh(@PathVariable Long maNganh);
}

package com.luanvan.profileservice.repository.httpClient;

import com.luanvan.profileservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user_service_url}")
public interface UserClient {
    @GetMapping("/auth/user/id/{maSo}")
    UserDTO getUserById(@PathVariable("maSo") String maSo);
}

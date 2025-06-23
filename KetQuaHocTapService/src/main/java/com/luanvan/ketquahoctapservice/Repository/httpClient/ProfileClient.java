package com.luanvan.ketquahoctapservice.Repository.httpClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "profile-service", url = "${profile_service_url}")
public interface ProfileClient {
    @GetMapping(value = "/sinhvien/{maSo}", produces = MediaType.APPLICATION_JSON_VALUE)
    Object getProfileByMaSo(@PathVariable("maSo") String maSo);
}

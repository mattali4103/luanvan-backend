package com.luanvan.kehoachhoctapservice.repository.httpClient;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name ="hoc-phan-service", url = "${hoc_phan_service_url}")
public interface HocPhanClient {
    @GetMapping(value ="/id/{maHocPhan}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean getHocPhanById(@PathVariable String maHocPhan);
}

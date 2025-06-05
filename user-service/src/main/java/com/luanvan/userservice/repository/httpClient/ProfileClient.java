package com.luanvan.userservice.repository.httpClient;

import com.luanvan.userservice.model.Request.CreateAdminRequest;
import com.luanvan.userservice.model.Request.CreateGiangVienRequest;
import com.luanvan.userservice.model.Request.CreateSinhVienRequest;
import com.luanvan.userservice.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "profile-service", url = "${profile_service_url}")
public interface ProfileClient {
    @PostMapping(value ="/sinh_vien/create", produces = MediaType.APPLICATION_JSON_VALUE)
    Object createSinhVien(@RequestBody CreateSinhVienRequest request);
    @PostMapping(value ="/admin/create", produces = MediaType.APPLICATION_JSON_VALUE)
    Object createAdmin(@RequestBody CreateAdminRequest request);
    @PostMapping(value ="/giang_vien/create", produces = MediaType.APPLICATION_JSON_VALUE)
    Object createGiangVien(@RequestBody CreateGiangVienRequest request);

}

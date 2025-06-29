package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.AdminDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.services.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ApiResponse<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO){
        return ApiResponse.<AdminDTO>builder()
                .code(200)
                .message("OK")
                .data(adminService.createAdmin(adminDTO))
                .build();
    }

    @GetMapping("/id/{maSo}")
    public AdminDTO getAdminByMaSo(@PathVariable String maSo){
        return adminService.findById(maSo);
    }

    @GetMapping("/me/{maSo}")
    public ApiResponse<ProfileResponse> getMyInfo(@PathVariable String maSo){
        return ApiResponse.<ProfileResponse>builder()
                .code(200)
                .message("OK")
                .data(adminService.getMyInfo(maSo))
                .build();
    }


    @PutMapping("/update")
    public ApiResponse<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO){
        return ApiResponse.<AdminDTO>builder()
                .code(200)
                .message("OK")
                .data(adminService.updateAdmin(adminDTO))
                .build();
    }

    @DeleteMapping("/delete/{maSo}")
    public ApiResponse<Void> deleteAdmin(@PathVariable String maSo){
        adminService.deleteAdmin(maSo);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("OK")
                .build();
    }
}

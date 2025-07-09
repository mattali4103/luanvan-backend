package com.luanvan.profileservice.controller;

import com.luanvan.profileservice.dto.CertificateDTO;
import com.luanvan.profileservice.dto.response.ApiResponse;
import com.luanvan.profileservice.services.CertificateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/profile/sinhvien/certificate")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/maso/{maSo}")
    public ApiResponse<List<CertificateDTO>> getCertificateByMaSo(@PathVariable String maSo) {
        return ApiResponse.<List<CertificateDTO>>builder()
                .code(200)
                .message("OK")
                .data(certificateService.getCertificatesByMaSo(maSo))
                .build();
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ApiResponse<CertificateDTO> uploadCertificate(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("tenChungChi") String tenChungChi,
            @RequestParam("maSo") String maSo,
            @RequestParam("ngayCap") String ngayCap
    ) {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setTenChungChi(tenChungChi);
        certificateDTO.getSinhVien().setMaSo(maSo);
        certificateDTO.setNgayCap(LocalDate.parse(ngayCap));

        return ApiResponse.<CertificateDTO>builder()
                .code(200)
                .message("Certificate uploaded successfully")
                .data(certificateService.uploadCertificate(file, certificateDTO))
                .build();
    }
    @PutMapping(value = "/update", consumes = "multipart/form-data")
    public ApiResponse<CertificateDTO> updateCertificate(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("id") Long id,
            @RequestParam("tenChungChi") String tenChungChi,
            @RequestParam("maSo") String maSo,
            @RequestParam("ngayCap") String ngayCap
    ) {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setId(id);
        certificateDTO.setTenChungChi(tenChungChi);
        certificateDTO.getSinhVien().setMaSo(maSo);
        certificateDTO.setNgayCap(LocalDate.parse(ngayCap));

        return ApiResponse.<CertificateDTO>builder()
                .code(200)
                .message("Certificate updated successfully")
                .data(certificateService.updateCertificate(file, certificateDTO))
                .build();
    }

}

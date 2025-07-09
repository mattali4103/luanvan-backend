package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.CertificateDTO;
import com.luanvan.profileservice.entity.Certificate;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final CertificateRepository certificateRepository;

    public CertificateDTO getCertificateById(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return modelMapper.map(certificate, CertificateDTO.class);
    }
    public List<CertificateDTO> getCertificatesByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<Certificate> certificates = certificateRepository.findBySinhVienMaSo(maSo);
        return certificates.stream()
                .map(certificate -> modelMapper.map(certificate, CertificateDTO.class))
                .toList();
    }

    @Transactional
    public CertificateDTO uploadCertificate(MultipartFile file, CertificateDTO dto) {
        if (dto == null || dto.getSinhVien().getMaSo() == null || dto.getSinhVien().getMaSo().isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        String imageUrl = cloudinaryService.uploadChungChi(file, dto.getSinhVien().getMaSo());
        dto.setImageUrl(imageUrl);
        Certificate certificate = modelMapper.map(dto, Certificate.class);
        certificateRepository.save(certificate);
        return modelMapper.map(certificate, CertificateDTO.class);
    }

    @Transactional
    public CertificateDTO updateCertificate(MultipartFile file, CertificateDTO certificateDTO) {
        if (certificateDTO == null || certificateDTO.getId() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        Certificate certificate = certificateRepository.findById(certificateDTO.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.typeMap(
                CertificateDTO.class, Certificate.class
        ).addMappings(mapper -> {
            mapper.skip(Certificate::setImageUrl);
        });

        modelMapper.map(certificateDTO, certificate);
        if (file != null && !file.isEmpty()) {
            // Xóa ảnh cũ nếu có
            if (certificate.getImageUrl() != null && !certificate.getImageUrl().isEmpty()) {
                cloudinaryService.deleteFile(certificate.getImageUrl());
            }
            // Tải ảnh mới lên
            String newImageUrl = cloudinaryService.uploadChungChi(file, certificateDTO.getSinhVien().getMaSo());
            certificate.setImageUrl(newImageUrl);
        }
        certificateRepository.save(certificate);
        return modelMapper.map(certificate, CertificateDTO.class);
    }
}

package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.SinhvienDTO;
import com.luanvan.profileservice.entity.SinhVien;
import com.luanvan.profileservice.repository.SinhVienRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SinhVienService {
    private final SinhVienRepository sinhVienRepository;

    public SinhVienService(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    public SinhvienDTO createSinhVien(SinhvienDTO sinhvienDTO){
        ModelMapper modelMapper = new ModelMapper();
        SinhVien sv = new SinhVien();
        modelMapper.map(sinhvienDTO, sv);
        sinhVienRepository.save(sv);
        return modelMapper.map(sv, SinhvienDTO.class);
    }
}

package com.luanvan.profileservice.config;

import com.luanvan.profileservice.dto.SinhVienDTO;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.entity.SinhVien;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper sinhVienToDTOMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(SinhVien.class, SinhVienDTO.class)
                .addMapping(sinhVien -> sinhVien.getLop().getMaLop(), SinhVienDTO::setMaSo);
        return modelMapper;
    }
}

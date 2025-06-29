package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.LopDTO;
import com.luanvan.profileservice.dto.NganhDTO;
import com.luanvan.profileservice.entity.Nganh;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.KhoaRepository;
import com.luanvan.profileservice.repository.NganhRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NganhService {

    private final ModelMapper modelMapper;
    private final NganhRepository nganhRepository;

    public NganhDTO createNganh(NganhDTO nganhDTO) {
        if(nganhDTO == null){
            throw new AppException(ErrorCode.INVALID_INPUT);
        }

        modelMapper.typeMap(NganhDTO.class, Nganh.class)
                .addMappings(mapper -> mapper.skip(Nganh::setMaNganh))
                .addMappings(mapper -> mapper.skip(Nganh::setDsLop));
        Nganh nganh = modelMapper.map(nganhDTO, Nganh.class);
        nganh = nganhRepository.save(nganh);
        return modelMapper.map(nganh, NganhDTO.class);
    }
    public NganhDTO getNganhByMaNganh(Long maNganh) {
        return nganhRepository.findById(maNganh)
                .map(nganh -> modelMapper.map(nganh, NganhDTO.class))
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
    }

    public NganhDTO updateNganh(NganhDTO nganhDTO) {
        if (!nganhRepository.existsById(nganhDTO.getMaNganh())) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        Nganh nganh = modelMapper.map(nganhDTO, Nganh.class);
        nganh.setMaNganh(nganhDTO.getMaNganh());
        nganh = nganhRepository.save(nganh);
        return modelMapper.map(nganh, NganhDTO.class);
    }
    public void deleteNganh(Long maNganh) {
        if (!nganhRepository.existsById(maNganh)) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        nganhRepository.deleteById(maNganh);
    }

    public boolean existByMaNganh(Long maNganh) {
        return nganhRepository.existsById(maNganh);
    }

    public List<LopDTO> getDanhSachLopByMaNganh(Long maNganh) {
        Nganh nganh = nganhRepository.findById(maNganh)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return nganh.getDsLop().stream()
                .map(lop -> modelMapper.map(lop, LopDTO.class))
                .toList();
    }


}

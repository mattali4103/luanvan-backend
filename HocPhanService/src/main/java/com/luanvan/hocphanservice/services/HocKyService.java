package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.HocKy;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.HocKyDTO;
import com.luanvan.hocphanservice.model.Request.HocKyRequest;
import com.luanvan.hocphanservice.model.Response.HocKyResponse;
import com.luanvan.hocphanservice.repository.HocKyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HocKyService {
    private final HocKyRepository hocKyRepository;
    ModelMapper modelMapper = new ModelMapper();

    public HocKyDTO findHocKyByName(String namBatDau, String namKetThuc, String tenHocKy) {
        HocKy hocKy = hocKyRepository.findNamHocByName(namBatDau, namKetThuc, tenHocKy);
        if (hocKy == null) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return modelMapper.map(hocKy, HocKyDTO.class);
    }

    public List<HocKyResponse> findHocKyIn(List<Long> hocPhanList) {
        if(hocPhanList.isEmpty()){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<HocKy> hocKyList = hocKyRepository.findByMaHocKyIn(hocPhanList);

        modelMapper.typeMap(HocKy.class, HocKyDTO.class).addMappings(mapper -> mapper.map(HocKy::getNamHoc, HocKyDTO::setNamHocDTO));

        return hocKyList.stream().map(hocKy -> modelMapper.map(hocKy, HocKyResponse.class)).toList();
    }

    public HocKyDTO create(HocKyRequest hocKyRequest) {
        if(hocKyRequest.getNamHocId() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        HocKy hocKy = modelMapper.map(hocKyRequest, HocKy.class);

        hocKyRepository.save(hocKy);
        return modelMapper.map(hocKy, HocKyDTO.class);
    }

    public HocKyDTO getHocKyById(Long maHocKy) {
        HocKy hocKy = hocKyRepository.findById(maHocKy)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        modelMapper.typeMap(HocKy.class, HocKyDTO.class).addMappings(mapper -> mapper.map(HocKy::getNamHoc, HocKyDTO::setNamHocDTO));
        return modelMapper.map(hocKy, HocKyDTO.class);
    }

    public HocKyDTO updateHocKy(Long maHocKy, HocKyDTO hocKyDTO) {
        HocKy hocKy = hocKyRepository.findById(maHocKy)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        modelMapper.map(hocKyDTO, hocKy);
        hocKyRepository.save(hocKy);
        return modelMapper.map(hocKy, HocKyDTO.class);
    }

    public List<HocKyDTO> getAllHocKy() {
        List<HocKy> hocKyList = hocKyRepository.findAll();
        return hocKyList.stream()
                .map(hocKy -> modelMapper.map(hocKy, HocKyDTO.class))
                .toList();
    }
}

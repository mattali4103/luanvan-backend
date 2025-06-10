package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.HocKy;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.HocKyDTO;
import com.luanvan.hocphanservice.model.Request.HocKyRequest;
import com.luanvan.hocphanservice.repository.HocKyRepository;
import com.luanvan.hocphanservice.repository.NamHocRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HocKyService {
    private final HocKyRepository hocKyRepository;
    private final NamHocRepository namHocRepository;
    ModelMapper modelMapper = new ModelMapper();

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

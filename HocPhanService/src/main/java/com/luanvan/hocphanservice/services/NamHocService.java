package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.NamHoc;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.NamHocDTO;
import com.luanvan.hocphanservice.repository.NamHocRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NamHocService {
    private final NamHocRepository namHocRepository;
    ModelMapper modelMapper = new ModelMapper();

    public NamHocDTO create(NamHocDTO namHocDTO) {
        namHocRepository.findByNamBatDau(namHocDTO.getNamBatDau())
                .ifPresent(namHoc -> {
                    throw new AppException(ErrorCode.EXISTED);
                });
        ;
        NamHoc namHoc = modelMapper.map(namHocDTO, NamHoc.class);
        namHocRepository.save(namHoc);
        return modelMapper.map(namHoc, NamHocDTO.class);
    }
    public NamHocDTO getNamHocById(Long maNamHoc) {
        NamHoc namHoc = namHocRepository.findById(maNamHoc)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return modelMapper.map(namHoc, NamHocDTO.class);
    }

    public NamHocDTO updateNamHoc(Long maNamHoc, NamHocDTO namHocDTO) {
        NamHoc namHoc = namHocRepository.findById(maNamHoc)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        modelMapper.map(namHocDTO, namHoc);
        namHocRepository.save(namHoc);
        return modelMapper.map(namHoc, NamHocDTO.class);
    }

    public List<NamHocDTO> getAllNamHoc() {
        List<NamHoc> namHocList = namHocRepository.findAll();
        return namHocList.stream()
                .map(namHoc -> modelMapper.map(namHoc, NamHocDTO.class))
                .toList();
    }

}

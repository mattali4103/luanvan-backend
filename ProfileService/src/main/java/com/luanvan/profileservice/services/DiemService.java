package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.DiemDTO;
import com.luanvan.profileservice.entity.Diem;

import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.DiemRepository;
import com.luanvan.profileservice.repository.SinhVienRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiemService {
    private final ModelMapper modelMapper;
    private final DiemRepository diemRepository;
    private final SinhVienRepository sinhVienRepository;

    public DiemDTO createDiem(DiemDTO diemDTO) {
        if(diemDTO == null) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }

        if(!sinhVienRepository.existsById(diemDTO.getSinhVienMaSo())){
            throw new AppException(ErrorCode.NOTFOUND);
        }

        if(diemDTO.getMaHocKy() == null || diemDTO.getDiemTB() == null) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }

        Diem diem = modelMapper.map(diemDTO, Diem.class);
        diemRepository.save(diem);
        return modelMapper.map(diem, DiemDTO.class);
    }

    public List<DiemDTO> getDiemByMaSo(String maSo) {
        if(maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
        List<Diem> diems = diemRepository.findDiemsBySinhVienMaSo(maSo);
        if(diems.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }

        return diems.stream()
                .map(diem -> modelMapper.map(diem, DiemDTO.class))
                .toList();
    }

    public DiemDTO getDiemByMaHocKy(Long maHocKy) {
        if(maHocKy == null) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
        Diem diem = diemRepository.findDiemByMaHocKy(maHocKy)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        return modelMapper.map(diem, DiemDTO.class);
    }
    public DiemDTO updateDiem(DiemDTO diemDTO) {
        if(diemDTO == null || diemDTO.getId() == null) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }

        Diem existingDiem = diemRepository.findById(diemDTO.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        modelMapper.map(diemDTO, existingDiem);
        diemRepository.save(existingDiem);
        return modelMapper.map(existingDiem, DiemDTO.class);
    }


}

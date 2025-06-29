package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.LopDTO;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.Nganh;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.LopRepository;
import com.luanvan.profileservice.repository.NganhRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LopService {
    private final LopRepository lopRepository;
    private final ModelMapper modelMapper;
    private final SinhVienService sinhVienService;
    private final NganhRepository nganhRepository;

    public List<LopDTO> getDSLopByMaKhoa(String maKhoa) {
        List<Nganh> nganhs = nganhRepository.findNganhsByMaKhoa(maKhoa);
        if (nganhs.isEmpty()) {
            return Collections.emptyList();
        }
        List<Lop> lops = lopRepository.findAllByNganhIn(nganhs);

        if (lops.isEmpty()) {
            return Collections.emptyList();
        }
        return lops.stream().map(lop -> {
                    LopDTO lopDTO = modelMapper.map(lop, LopDTO.class);
                    lopDTO.setDSSinhVien(null);
                    return lopDTO;
                }).toList();
    }

    public LopDTO getLopByMaLop(String maLop) {
        return lopRepository.findById(maLop)
                .map(lop -> modelMapper.map(lop, LopDTO.class))
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
    }

    public List<ProfileResponse> getSinhVienProfileByLop(String maLop) {
        Lop lop = lopRepository.findById(maLop)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        List<ProfileResponse> list = sinhVienService.getAllSinhVienByLop(lop);
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return list;
    }
}

package com.luanvan.ketquahoctapservice.service;

import com.luanvan.ketquahoctapservice.Repository.KetQuaHocTapRepository;
import com.luanvan.ketquahoctapservice.Repository.httpClient.ProfileClient;
import com.luanvan.ketquahoctapservice.entity.KetQuaHocTap;
import com.luanvan.ketquahoctapservice.exception.AppException;
import com.luanvan.ketquahoctapservice.exception.ErrorCode;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDTO;
import com.luanvan.ketquahoctapservice.model.dto.SinhvienDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KetQuaHocTapService {
    private final KetQuaHocTapRepository ketQuaHocTapRepository;
    private final ModelMapper modelMapper;
    private final ProfileClient profileClient;
    public SinhvienDTO findBySinhVienId(String maSo) {
        var sinhvien = profileClient.getProfileByMaSo(maSo);
        return modelMapper.map(sinhvien, SinhvienDTO.class);
    }
    public List<KetQuaHocTapDTO> creates(List<KetQuaHocTapDTO> ketQuaHocTapDTOList) {
        if (ketQuaHocTapDTOList == null || ketQuaHocTapDTOList.isEmpty()) {
            throw new AppException(ErrorCode.KET_QUA_HOC_TAP_EMPTY);
        }

        List<KetQuaHocTap> ketQuaHocTapList = new ArrayList<>();
        for (KetQuaHocTapDTO dto : ketQuaHocTapDTOList) {
            KetQuaHocTap ketQuaHocTap = modelMapper.map(dto, KetQuaHocTap.class);
            ketQuaHocTapList.add(ketQuaHocTap);
        }
        List<KetQuaHocTap> savedKetQuaHocTaps = ketQuaHocTapRepository.saveAll(ketQuaHocTapList);
        List<KetQuaHocTapDTO> result = new ArrayList<>();
        for (KetQuaHocTap saved : savedKetQuaHocTaps) {
            result.add(modelMapper.map(saved, KetQuaHocTapDTO.class));
        }
        return result;
    }
    public KetQuaHocTapDTO create(KetQuaHocTapDTO ketQuaHocTapDTO) {
        if (ketQuaHocTapDTO == null) {
            throw new AppException(ErrorCode.KET_QUA_HOC_TAP_EMPTY);
        }
        KetQuaHocTap ketQuaHocTap = modelMapper.map(ketQuaHocTapDTO, KetQuaHocTap.class);
        KetQuaHocTap savedKetQuaHocTap = ketQuaHocTapRepository.save(ketQuaHocTap);
        return modelMapper.map(savedKetQuaHocTap, KetQuaHocTapDTO.class);
    }
}

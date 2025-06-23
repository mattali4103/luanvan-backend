package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.NhomHP;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.NhomHpDTO;
import com.luanvan.hocphanservice.repository.NhomHPRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhomHPService {
    private final NhomHPRepository nhomHPRepository;
    private final ModelMapper modelMapper;


    public NhomHpDTO create(NhomHpDTO nhomHpDTO) {
        if(nhomHPRepository.findById(nhomHpDTO.getMaNhomHP()).isPresent()) {
            throw new AppException(ErrorCode.EXISTED);
        }
        NhomHP nhomHP = new NhomHP();
        modelMapper.map(nhomHpDTO, nhomHP);

        nhomHPRepository.save(nhomHP);

        return modelMapper.map(nhomHP, NhomHpDTO.class);
    }
    public List<NhomHpDTO> createList(List<NhomHpDTO> nhomHpDTO) {
        List<NhomHP> nhomHPList = nhomHpDTO.stream()
                .map(dto -> modelMapper.map(dto, NhomHP.class))
                .toList();
        nhomHPRepository.saveAll(nhomHPList);
        return nhomHPList.stream()
                .map(nhomHP -> modelMapper.map(nhomHP, NhomHpDTO.class))
                .toList();
    }
    public NhomHpDTO getNhomHPById(String maNhomHP) {
        NhomHP nhomHP = nhomHPRepository.findById(maNhomHP)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return modelMapper.map(nhomHP, NhomHpDTO.class);
    }

    public List<NhomHpDTO> getAllNhomHP() {
        List<NhomHP> nhomHPList = nhomHPRepository.findAll();
        return nhomHPList.stream()
                .map(nhomHP -> modelMapper.map(nhomHP, NhomHpDTO.class))
                .toList();
    }

}

package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.HocPhan;
import com.luanvan.hocphanservice.entity.HocPhanTuChon;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.HocPhanTuChonDTO;
import com.luanvan.hocphanservice.repository.ChuongTrinhDaoTaoRepository;
import com.luanvan.hocphanservice.repository.HocPhanRepository;
import com.luanvan.hocphanservice.repository.HocPhanTuChonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HocPhanTuChonService {
    private final ModelMapper modelMapper;
    private final HocPhanTuChonRepository hocPhanTuChonRepository;
    private final HocPhanRepository hocPhanRepository;
    private final ChuongTrinhDaoTaoRepository chuongTrinhDaoTaoRepository;

    public HocPhanTuChonDTO getHocPhanTuChonByNameAndKhoaHoc(String tenNhom, String khoaHoc) {
        HocPhanTuChon hptc = hocPhanTuChonRepository.findByTenNhomLikeAndChuongTrinhDaoTao_KhoaHoc("%" + tenNhom + "%", khoaHoc)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_REQUEST));

        return modelMapper.map(hptc, HocPhanTuChonDTO.class);
    }

    public HocPhanTuChonDTO create(HocPhanTuChonDTO dto){
        validate(dto);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(HocPhanTuChonDTO.class, HocPhanTuChon.class).addMappings(config -> config.skip(HocPhanTuChon::setHocPhanTuChonList));
        HocPhanTuChon hocPhanTuChon = modelMapper.map(dto, HocPhanTuChon.class);
        if(dto.getHocPhanTuChonList() != null && !dto.getHocPhanTuChonList().isEmpty()){
            List<HocPhan> hocPhanList = new LinkedList<>();
            dto.getHocPhanTuChonList().forEach(hocPhanDTO -> {
                HocPhan hocPhan = hocPhanRepository.findById(hocPhanDTO.getMaHp()).orElse(new HocPhan());
                hocPhanList.add(hocPhan);
            });
            hocPhanTuChon.setHocPhanTuChonList(hocPhanList);
        }
        hocPhanTuChon.setChuongTrinhDaoTao(chuongTrinhDaoTaoRepository.findById(dto.getKhoaHoc()).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_REQUEST)
        ));
        return modelMapper.map(hocPhanTuChonRepository.save(hocPhanTuChon), HocPhanTuChonDTO.class);
    }

    public HocPhanTuChonDTO addHocPhanTuChon(Long id, List<HocPhanDTO> dtoList){
        validate(Collections.singletonList(dtoList));
        HocPhanTuChon hocPhanTuChon = hocPhanTuChonRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_REQUEST)
        );
        List<HocPhan> hocPhanList = new LinkedList<>();
        dtoList.forEach(hocPhanDTO -> {
            HocPhan hocPhan = hocPhanRepository.findById(hocPhanDTO.getMaHp()).orElse(new HocPhan());
            hocPhanList.add(hocPhan);
        });
        hocPhanTuChon.setHocPhanTuChonList(hocPhanList);
        hocPhanTuChonRepository.save(hocPhanTuChon);
        return modelMapper.map(hocPhanTuChon, HocPhanTuChonDTO.class);
    }
    public HocPhanTuChonDTO getHocPhanTuChon(Long id){
        validate(id);
        HocPhanTuChon hocPhanTuChon = hocPhanTuChonRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_REQUEST)
        );
        return modelMapper.map(hocPhanTuChon, HocPhanTuChonDTO.class);
    }

    public void deleteHocPhanTuChon(Long id){
        validate(id);
        hocPhanTuChonRepository.deleteById(id);
    }

    private void validate(Long id){
        if(id == null){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }
    private void validate(Object object){
        if(object == null){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }
    private void validate(List<Object> list){
        if(list == null || list.isEmpty()){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

}

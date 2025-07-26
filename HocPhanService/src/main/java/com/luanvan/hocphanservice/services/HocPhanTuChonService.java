package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
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

    public List<HocPhanTuChonDTO> getHocPhanTuChonByNameAndKhoaHoc(String tenNhom, String khoaHoc, Long maNganh) {
        List<HocPhanTuChon> hptcList = hocPhanTuChonRepository.findByTenNhomLikeAndChuongTrinhDaoTaoAndKhoaHocAndMaNganh("%" + tenNhom + "%", khoaHoc, maNganh);
        if (hptcList.isEmpty()) {
            return Collections.emptyList();
        }
        return hptcList.stream().map(hocPhanTuChon -> modelMapper.map(hocPhanTuChon, HocPhanTuChonDTO.class)).toList();
    }

    public HocPhanTuChonDTO create(HocPhanTuChonDTO dto) {
        validate(dto);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(HocPhanTuChonDTO.class, HocPhanTuChon.class).addMappings(config -> config.skip(HocPhanTuChon::setHocPhanTuChonList));
        HocPhanTuChon hocPhanTuChon = modelMapper.map(dto, HocPhanTuChon.class);
        if (dto.getHocPhanTuChonList() != null && !dto.getHocPhanTuChonList().isEmpty()) {
            List<HocPhan> hocPhanList = new LinkedList<>();
            dto.getHocPhanTuChonList().forEach(hocPhanDTO -> {
                HocPhan hocPhan = hocPhanRepository.findById(hocPhanDTO.getMaHp()).orElse(new HocPhan());
                hocPhanList.add(hocPhan);
            });
            hocPhanTuChon.setHocPhanTuChonList(hocPhanList);
        }
        hocPhanTuChon.setChuongTrinhDaoTao(chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(dto.getKhoaHoc(), dto.getMaNganh()).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_REQUEST)
        ));
        return modelMapper.map(hocPhanTuChonRepository.save(hocPhanTuChon), HocPhanTuChonDTO.class);
    }

    public HocPhanTuChonDTO addHocPhanTuChon(Long id, List<HocPhanDTO> dtoList) {
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


    public HocPhanTuChonDTO getHocPhanTuChon(Long id) {
        validate(id);
        HocPhanTuChon hocPhanTuChon = hocPhanTuChonRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_REQUEST)
        );
        return modelMapper.map(hocPhanTuChon, HocPhanTuChonDTO.class);
    }

    public List<HocPhanTuChonDTO> getAllByKhoaHocAndMaNganh(String khoaHoc, Long maNganh) {
        validate(khoaHoc);
        validate(maNganh);
        List<HocPhanTuChon> hocPhanTuChonList = hocPhanTuChonRepository.findAllCTDTByKhoaHocAndMaNganh(khoaHoc, maNganh);
        if (hocPhanTuChonList.isEmpty()) {
            return Collections.emptyList();
        }
        return hocPhanTuChonList.stream()
                .map(hocPhanTuChon -> modelMapper.map(hocPhanTuChon, HocPhanTuChonDTO.class))
                .toList();
    }


    // Tạo từ danh sách các nhóm học phần tự chọn method cho ChuongTrinhDaoTaoService
    public void creates(List<HocPhanTuChon> nhomHocPhanTuChon, String khoaHoc, Long maNganh) {
        if (nhomHocPhanTuChon == null || nhomHocPhanTuChon.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        for (HocPhanTuChon hocPhanTuChon : nhomHocPhanTuChon) {
            validate(hocPhanTuChon);
            hocPhanTuChon.setChuongTrinhDaoTao(chuongTrinhDaoTaoRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh)
                    .orElseThrow(() -> new AppException(ErrorCode.INVALID_REQUEST)));
            if (hocPhanTuChon.getHocPhanTuChonList() != null && !hocPhanTuChon.getHocPhanTuChonList().isEmpty()) {
                List<HocPhan> hocPhanList = new LinkedList<>();
                hocPhanTuChon.getHocPhanTuChonList().forEach(hocPhan -> {
                    HocPhan hp = hocPhanRepository.findById(hocPhan.getMaHp()).orElse(new HocPhan());
                    hocPhanList.add(hp);
                });
                hocPhanTuChon.setHocPhanTuChonList(hocPhanList);
            }
        }
    }

    public void deleteHocPhanTuChon(Long id) {
        validate(id);
        hocPhanTuChonRepository.deleteById(id);
    }

    public void deleteHocPhanInHocPhanTuChon(Long id, List<String> maHp) {
        validate(id);
        validate(maHp);
        HocPhanTuChon hocPhanTuChon = hocPhanTuChonRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOTFOUND)
        );
        List<HocPhan> hocPhanList = hocPhanTuChon.getHocPhanTuChonList();
        if (hocPhanList == null || hocPhanList.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        hocPhanList.removeIf(hocPhan -> maHp.contains(hocPhan.getMaHp()));
        hocPhanTuChon.setHocPhanTuChonList(hocPhanList);
        hocPhanTuChonRepository.save(hocPhanTuChon);
    }

    private void validate(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    private void validate(Object object) {
        if (object == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    private void validate(List<Object> list) {
        if (list == null || list.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    public List<HocPhanDTO> getNhomHocPhanTheChat() {
        HocPhanTuChon hptc = hocPhanTuChonRepository.findById(4L)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        if (hptc.getHocPhanTuChonList().isEmpty()) {
            return Collections.emptyList();
        }
        return hptc.getHocPhanTuChonList().stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        validate(id);
        if (!hocPhanTuChonRepository.existsById(id)) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        hocPhanTuChonRepository.deleteById(id);
    }
    // Tạo từ ChuongTrinhDaoTaoDTO, hocPhan chỉ có maHp, yêu cầu phải truyền vào id của ctdt
    public HocPhanTuChon createFromCTDT(ChuongTrinhDaoTao ctdt, HocPhanTuChonDTO dto) {
        validate(dto);
        HocPhanTuChon hocPhanTuChon = modelMapper.map(dto, HocPhanTuChon.class);
        List<HocPhan> hocPhanList = new LinkedList<>();
        dto.getHocPhanTuChonList().forEach(hocPhanDTO -> {
            HocPhan hocPhan = hocPhanRepository.findById(hocPhanDTO.getMaHp()).orElse(new HocPhan());
            hocPhanList.add(hocPhan);
        });
        hocPhanTuChon.setHocPhanTuChonList(hocPhanList);
        hocPhanTuChon.setChuongTrinhDaoTao(ctdt);
        hocPhanTuChonRepository.save(hocPhanTuChon);
        return hocPhanTuChon;
    }
}

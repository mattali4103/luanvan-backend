package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.KhoaDTO;
import com.luanvan.profileservice.entity.Khoa;
import com.luanvan.profileservice.entity.Nganh;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.KhoaRepository;import com.luanvan.profileservice.repository.NganhRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KhoaService {
    private final KhoaRepository khoaRepository;
    private final ModelMapper modelMapper;
    private final NganhRepository nganhRepository;

    public KhoaDTO createKhoa(KhoaDTO khoaDTO) {
        modelMapper.typeMap(KhoaDTO.class, Khoa.class).addMappings(mapper -> mapper.skip(Khoa::setDSNganh));
        Khoa khoa = modelMapper.map(khoaDTO, Khoa.class);
        khoa = khoaRepository.save(khoa);
        return modelMapper.map(khoa, KhoaDTO.class);
    }

    public KhoaDTO addNganhToKhoa(Long maKhoa, Long maNganh) {
        checkExistedNganhInKhoa(maKhoa, maNganh);
        Khoa khoa = khoaRepository.findById(maKhoa)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        Nganh nganh = nganhRepository.findById(maNganh)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        if (khoa.getDSNganh() != null) {
            khoa.getDSNganh().add(nganh);
        }
        nganh.setKhoa(khoa);
        khoaRepository.save(khoa);
        return modelMapper.map(khoa, KhoaDTO.class);
    }


    private void checkExistedNganhInKhoa(Long maKhoa, Long maNganh) {
        Khoa khoa = khoaRepository.findById(maKhoa)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        if (khoa.getDSNganh() != null) {
            khoa.getDSNganh().stream()
                    .filter(nganh -> nganh.getMaNganh().equals(maNganh))
                    .findFirst()
                    .ifPresent(nganh -> {
                        throw new AppException(ErrorCode.EXISTED);
                    });
        }
        Nganh nganh = nganhRepository.findById(maNganh)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        if (khoa.getDSNganh() != null) {
            khoa.getDSNganh().add(nganh);
        }
        nganh.setKhoa(khoa);
    }

    public KhoaDTO getKhoaByMaKhoa(Long maKhoa) {
        return khoaRepository.findById(maKhoa)
                .map(khoa -> modelMapper.map(khoa, KhoaDTO.class))
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
    }

    public KhoaDTO updateKhoa(Long maKhoa, KhoaDTO khoaDTO) {
        if (!khoaRepository.existsById(maKhoa)) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        modelMapper.typeMap(KhoaDTO.class, Khoa.class).addMappings(mapper -> mapper.skip(Khoa::setDSNganh));
        Khoa khoa = modelMapper.map(khoaDTO, Khoa.class);
        khoa.setMaKhoa(maKhoa);
        khoa = khoaRepository.save(khoa);
        return modelMapper.map(khoa, KhoaDTO.class);
    }
    public void deleteKhoa(Long maKhoa) {
        if (!khoaRepository.existsById(maKhoa)) {
            throw new AppException(ErrorCode.NOTFOUND);
        }

        // Xóa tất cả các ngành thuộc khoa này
        khoaRepository.findById(maKhoa).ifPresent(khoa -> {
            assert khoa.getDSNganh() != null;
            khoa.getDSNganh().forEach(nganh -> {
                nganh.setKhoa(null);
                nganhRepository.save(nganh);
            });
        });

        khoaRepository.deleteById(maKhoa);
    }
}

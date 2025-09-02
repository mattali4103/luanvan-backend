package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.GiangVienDTO;
import com.luanvan.profileservice.dto.LopDTO;
import com.luanvan.profileservice.dto.UserDTO;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.dto.response.SinhVienPreviewProfile;
import com.luanvan.profileservice.dto.response.StatisticsLopResponse;
import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.Nganh;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.LopRepository;
import com.luanvan.profileservice.repository.NganhRepository;
import com.luanvan.profileservice.repository.httpClient.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LopService {
    private final LopRepository lopRepository;
    private final ModelMapper modelMapper;
    private final SinhVienService sinhVienService;
    private final NganhRepository nganhRepository;
    private final UserClient userClient;

    public List<LopDTO> getDSLopByChuNhiem(String maGiangVien) {
        List<Lop> lops = lopRepository.findByChuNhiem_MaSo(maGiangVien);
        if (lops.isEmpty()) {
            return Collections.emptyList();
        }
        return lops.stream().map(lop -> {
            LopDTO lopDTO = modelMapper.map(lop, LopDTO.class);
            lopDTO.setDSSinhVien(null);

            // Mapping GiangVien thành GiangVienDTO nếu có chủ nhiệm
            if (lop.getChuNhiem() != null) {
                GiangVienDTO giangVienDTO = modelMapper.map(lop.getChuNhiem(), GiangVienDTO.class);
                lopDTO.setChuNhiem(giangVienDTO);
            }

            return lopDTO;
        }).toList();
    }

    public StatisticsLopResponse getStatisticsByChuNhiem(String maGiangVien) {
        List<Lop> danhSachLop = lopRepository.findByChuNhiem_MaSo(maGiangVien);
        if(danhSachLop.isEmpty()) {
            return new StatisticsLopResponse(0L, 0L);
        }
        StatisticsLopResponse result = new StatisticsLopResponse();

        // Tính tổng số lớp
        for(Lop lop : danhSachLop) {
            Long siSo = lopRepository.countSinhVienByMaLop(lop.getMaLop());
            result.setTongSoSinhVien(result.getTongSoSinhVien() + siSo);
        }
        log.info("Tổng số sinh viên trong các lớp do giảng viên {} chủ nhiệm: {}", maGiangVien, result.getTongSoSinhVien());

        result.setSoLopHoc((long) danhSachLop.size());
        return result;
    }

    public StatisticsLopResponse getStatistics(Long maNganh) {
        List<Lop> danhSachLop = lopRepository.findByNganhMaNganh(maNganh);
        if(danhSachLop.isEmpty()) {
            return new StatisticsLopResponse(0L, 0L);
        }
        StatisticsLopResponse result = new StatisticsLopResponse();

        // Tính tổng số lớp
        for(Lop lop : danhSachLop) {
            Long siSo = lopRepository.countSinhVienByMaLop(lop.getMaLop());
            log.info("Lớp: {}, Sĩ số: {}", lop.getMaLop(), siSo);
            result.setTongSoSinhVien(result.getTongSoSinhVien() + siSo);


        }
        log.info("Tổng số sinh viên trong ngành {}: {}", maNganh, result.getTongSoSinhVien());

        result.setSoLopHoc((long) danhSachLop.size());
        return result;
    }

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
        Lop lop = lopRepository.findById(maLop)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        LopDTO lopDTO = modelMapper.map(lop, LopDTO.class);
        lopDTO.setSiSoCon(lopRepository.countSinhVienByMaLop(maLop));
        return lopDTO;
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
    public LopDTO getLopInfoByMaLop(String maLop) {
        Lop lop = lopRepository.findById(maLop)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        LopDTO lopDTO = modelMapper.map(lop, LopDTO.class);

        UserDTO chuNhiem = userClient.getUserById(lopDTO.getChuNhiem().getMaSo());
        lopDTO.getChuNhiem().setHoTen(chuNhiem.getHoTen());
        lopDTO.getChuNhiem().setEmail(chuNhiem.getEmail());

        lopDTO.setSiSoCon(lopRepository.countSinhVienByMaLop(maLop));
        lopDTO.setDSSinhVien(null); // Không cần danh sách sinh viên trong thông tin lớp
        return lopDTO;
    }

    public List<SinhVienPreviewProfile> getPreviewProfileByMaLop(String maLop) {
        Lop lop = lopRepository.findById(maLop)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        List<SinhVienPreviewProfile> list = new ArrayList<>();
        lop.getDSSinhVien().forEach(sinhVien -> {
            SinhVienPreviewProfile previewProfile = sinhVienService.getSinhVienPreviewProfile(sinhVien.getMaSo());
            if (previewProfile != null) {
                list.add(previewProfile);
            }
        });
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return list;
    }
}

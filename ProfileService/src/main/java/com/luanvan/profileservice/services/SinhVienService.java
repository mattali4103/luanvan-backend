package com.luanvan.profileservice.services;

import com.luanvan.profileservice.dto.SinhVienDTO;
import com.luanvan.profileservice.dto.UserDTO;
import com.luanvan.profileservice.dto.request.CreateSinhVienRequest;
import com.luanvan.profileservice.dto.response.ProfileResponse;
import com.luanvan.profileservice.dto.response.SinhVienPreviewProfile;
import com.luanvan.profileservice.dto.response.ThongKeKetQuaSinhVien;
import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.SinhVien;
import com.luanvan.profileservice.exception.AppException;
import com.luanvan.profileservice.exception.ErrorCode;
import com.luanvan.profileservice.repository.LopRepository;
import com.luanvan.profileservice.repository.SinhVienRepository;
import com.luanvan.profileservice.repository.httpClient.KHHTClient;
import com.luanvan.profileservice.repository.httpClient.KQHTClient;
import com.luanvan.profileservice.repository.httpClient.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SinhVienService {
    private final SinhVienRepository sinhVienRepository;
    private final ModelMapper modelMapper;
    private final LopRepository lopRepository;
    private final ModelMapper sinhVienToDTOMapper;
    private final UserClient userClient;
    private final KHHTClient kHHTClient;
    private final KQHTClient kQHTClient;
    private final CloudinaryService cloudinaryService;
    @Transactional
    public void deleteSinhVien(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        sinhVienRepository.delete(sinhVien);
    }


    @Transactional
    public SinhVienDTO updateSinhVien(SinhVienDTO sinhvienDTO) {
        if(sinhvienDTO == null || sinhvienDTO.getMaSo() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // Cấu hình mapping cho ngayCapCCCD từ String sang LocalDate
        modelMapper.createTypeMap(String.class, LocalDate.class)
            .setConverter(context -> {
                String source = context.getSource();
                if (source == null || source.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(source);
                } catch (Exception e) {
                    throw new AppException(ErrorCode.INVALID_REQUEST);
                }
            });

        modelMapper.typeMap(SinhVienDTO.class, SinhVien.class)
                .addMappings(mapper -> {
                    mapper.skip(SinhVien::setLop);
                    mapper.skip(SinhVien::setKhoaHoc);
                    mapper.skip(SinhVien::setMaSo);
                });

        SinhVien sv = sinhVienRepository.findById(sinhvienDTO.getMaSo())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        modelMapper.map(sinhvienDTO, sv);
        sinhVienRepository.save(sv);
        return sinhVienToDTOMapper.map(sv, SinhVienDTO.class);
    }

    @Transactional
    public SinhVienDTO createSinhVien(CreateSinhVienRequest request){
        Lop lop = lopRepository.findById(request.getMaLop())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        //ModelMapper skip nullable field
        // to prevent overwriting the Lop field in SinhVien entity
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.typeMap(SinhVienDTO.class, SinhVien.class)
                .addMappings(mapper ->
                        mapper.skip(SinhVien::setLop));
        SinhVien sv = new SinhVien();
        modelMapper.map(request, sv);
        sv.setLop(lop);
        sinhVienRepository.save(sv);
        return sinhVienToDTOMapper.map(sv, SinhVienDTO.class);
    }

    public String uploadAvatar(MultipartFile file, String maSo) {
        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        // Xóa avatar cũ nếu có
        if (sinhVien.getAvatarUrl() != null && !sinhVien.getAvatarUrl().isEmpty()) {
            cloudinaryService.deleteFile(sinhVien.getAvatarUrl());
        }

        String avatarUrl = cloudinaryService.uploadFile(file, "avatars/" + maSo);
        sinhVien.setAvatarUrl(avatarUrl);
        sinhVienRepository.save(sinhVien);
        return avatarUrl;
    }

    public SinhVienDTO findById(String maSo) {
        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        return modelMapper.map(sinhVien, SinhVienDTO.class);
    }

    public List<ProfileResponse> getAllSinhVienByLop(Lop lop) {
        List<SinhVien> sinhVienList = sinhVienRepository.findSinhViensByLop(lop);
        if (sinhVienList.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return sinhVienList.stream().map(this::getProfileResponse).toList();
    }
    public ProfileResponse getMyInfo(String maSo){
        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        return getProfileResponse(sinhVien);
    }

    public SinhVienPreviewProfile getSinhVienPreviewProfile(String maSo) {
        SinhVien sinhVien = sinhVienRepository.findById(maSo)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        UserDTO userDTO = userClient.getUserById(sinhVien.getMaSo());
        Long countTinChiDangKy = kHHTClient.getCountTinChiDangKyByMaSo(maSo);
        if (countTinChiDangKy == null) {
            countTinChiDangKy = 0L;
        }
        ThongKeKetQuaSinhVien kqht =  kQHTClient.getThongKeByMaSo(maSo);
        if(kqht == null) {
            kqht = new ThongKeKetQuaSinhVien();
        }

        return SinhVienPreviewProfile.builder()
                .avatarUrl(sinhVien.getAvatarUrl())
                .gioiTinh(userDTO.isGioiTinh())
                .maSo(sinhVien.getMaSo())
                .hoTen(userDTO.getHoTen())
                .khoaHoc(sinhVien.getKhoaHoc())
                .ngaySinh(userDTO.getNgaySinh().format(DateTimeFormatter.ISO_DATE))
                .maLop(sinhVien.getLop().getMaLop())
                .tenNganh(sinhVien.getLop().getNganh().getTenNganh())
                .xepLoaiHocLuc(kqht.getXepLoai())
                .diemTrungBinhTichLuy(kqht.getDiemTBTichLuy())
                .soTinChiTichLuy(kqht.getSoTinChiTichLuy())
                .soTinChiCaiThien(kqht.getSoTinChiCaiThien())
                .soTinChiDangKyHienTai(countTinChiDangKy)
                .build();
    }



    private ProfileResponse getProfileResponse(SinhVien sinhVien) {
        UserDTO userDTO = userClient.getUserById(sinhVien.getMaSo());
        ProfileResponse response = modelMapper.map(sinhVien, ProfileResponse.class);
        response.setMaNganh(sinhVien.getLop().getNganh().getMaNganh());
        response.setTenNganh(sinhVien.getLop().getNganh().getTenNganh());
        response.setMaSo(userDTO.getMaSo());
        response.setHoTen(userDTO.getHoTen());
        response.setNgaySinh(userDTO.getNgaySinh());
        response.setGioiTinh(userDTO.isGioiTinh());
        return response;
    }



}

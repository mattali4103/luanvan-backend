package com.luanvan.kehoachhoctapservice.service;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTap;
import com.luanvan.kehoachhoctapservice.exception.AppException;
import com.luanvan.kehoachhoctapservice.exception.ErrorCode;

import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import com.luanvan.kehoachhoctapservice.model.dto.HocPhanDTO;
import com.luanvan.kehoachhoctapservice.model.dto.KeHoachHocTapDTO;
import com.luanvan.kehoachhoctapservice.model.request.AddKHHTRequest;
import com.luanvan.kehoachhoctapservice.model.request.HocPhanRequest;
import com.luanvan.kehoachhoctapservice.model.request.KeHoachHocTapRequest;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapDetail;
import com.luanvan.kehoachhoctapservice.model.response.TinChiResponse;
import com.luanvan.kehoachhoctapservice.repository.KeHoachHocTapRepository;
import com.luanvan.kehoachhoctapservice.repository.httpClient.HocPhanClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeHoachHocTapService {
    private final ModelMapper modelMapper;
    private final KeHoachHocTapRepository keHoachHocTapRepository;
    private final HocPhanClient hocPhanClient;


    public TinChiResponse countKeHoachHocTapsByMaSo(String maSo, String khoaHoc) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        if (khoaHoc == null || khoaHoc.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<KeHoachHocTap> khhtList = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);

        if(khhtList == null || khhtList.isEmpty()) {
            throw new AppException(ErrorCode.KHHT_EXISTED_NOTFOUND);
        }
        modelMapper.typeMap(KeHoachHocTap.class, KeHoachHocTapRequest.class)
                .addMappings(mapper -> {
                    mapper.map(KeHoachHocTap::getMaHocPhan, KeHoachHocTapRequest::setMaHocPhan);
                });
        List<KeHoachHocTapRequest> request = khhtList.stream().map(khht -> modelMapper.map(khht, KeHoachHocTapRequest.class)).toList();
        return hocPhanClient.getCountTinChiByCTDT(khoaHoc, request);
    }

    //  Lấy số lượng kế hoạch học tập theo mã số sinh viên và học kỳ
//    public KeHoachHocTapCountResponse countKeHoachHocTapByMaSoAndMaHocKy(KeHoachHocTapRequest request) {
//        if (request.getMaSo() == null || request.getMaSo().isEmpty() || request.getMaHocKy() == null) {
//            throw new AppException(ErrorCode.INVALID_REQUEST);
//        }
//        Long soHocPhanDangKy = keHoachHocTapRepository.countKeHoachHocTapByMaSoAndMaHocKy(request.getMaSo(), request.getMaHocKy());
//        Long soHocPhanCaiThien = keHoachHocTapRepository.countKeHoachHocTapByMaSoAndMaHocKyAndHocPhanCaiThien(request.getMaSo(), request.getMaHocKy(), true);
//        return KeHoachHocTapCountResponse.builder()
//                .soHocPhanCaiThien(soHocPhanCaiThien)
//                .soHocPhanDangKy(soHocPhanDangKy)
//                .build();
//    }

    @Transactional
    public KeHoachHocTapDTO create(AddKHHTRequest request) {
        if(request == null)
        {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        //        Kiểm tra học phần này con tồn tại trong hệ thống không
        if (isHocPhanExist(request.getMaHocPhan())) {
            throw new AppException(ErrorCode.HOCPHAN_NOTFOUND);
        }
//      Kiểm tra học phần đang nhập có phải là học phần cải thiện không
        if (request.isHocPhanCaiThien() && !isKeHoachHocTapExist(request.getMaSo(), request.getMaHocPhan())) {
            throw new AppException(ErrorCode.KHHT_NOTFOUND);
        }

        KeHoachHocTap khht = modelMapper.map(request, KeHoachHocTap.class);

        keHoachHocTapRepository.save(khht);
        return modelMapper.map(khht, KeHoachHocTapDTO.class);
    }

    public List<HocPhanDTO> getHocPhanNotInCTDT(String maSo, String khoaHoc){
        List<String> maHpInKHHT = keHoachHocTapRepository.findMaHocPhanByMaSo(maSo);
        return hocPhanClient.getHocPhanNotInCTDT(khoaHoc, maHpInKHHT);
    }

//    Lấy học phần có trong khht
    public List<KeHoachHocTapDetail> getKHHTDetailByLoaiHP(String maSo, String khoaHoc, String loaiHp){

        List<KeHoachHocTapDTO> khhtList = getKeHoachHocTapsByMaSo(maSo);
        List<Long> maHocKyList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocKy)
                .distinct()
                .toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanInCTDTByLoaiHp(HocPhanRequest.builder().khoaHoc(khoaHoc).loaiHp(loaiHp).build());
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);

        return getKeHoachHocTapDetails(khhtList, hocPhanDTOList, hocKyDTOList);
    }

    private List<KeHoachHocTapDetail> getKeHoachHocTapDetails(List<KeHoachHocTapDTO> khhtList, List<HocPhanDTO> hocPhanDTOList, List<HocKyDTO> hocKyDTOList) {
        Map<String, HocPhanDTO> hocPhanMap = hocPhanDTOList.stream()
                .collect(Collectors.toMap(HocPhanDTO::getMaHp, Function.identity()));
        Map<Long, HocKyDTO> hocKyMap = hocKyDTOList.stream()
                .collect(Collectors.toMap(HocKyDTO::getMaHocKy, Function.identity()));

        List<KeHoachHocTapDetail> khhtDetailList = new LinkedList<>();
        khhtList.forEach(khht -> {
            HocPhanDTO hocPhanDTO = hocPhanMap.get(khht.getMaHocPhan());
            HocKyDTO hocKyDTO = hocKyMap.get(khht.getMaHocKy());

            if (hocPhanDTO != null && hocKyDTO != null) {
                KeHoachHocTapDetail khhtDetail = new KeHoachHocTapDetail();
                khhtDetail.setHocKy(hocKyDTO);
                khhtDetail.setHocPhan(hocPhanDTO);
                khhtDetail.setNamHoc(hocKyDTO.getNamHocDTO());
                khhtDetail.setId(khht.getId());
                khhtDetail.setLoaiHocPhan(hocPhanDTO.getLoaiHp());
                khhtDetail.setHocPhanCaiThien(khht.isHocPhanCaiThien());
                khhtDetailList.add(khhtDetail);
            }
        });
        return khhtDetailList;
    }

    public List<KeHoachHocTapDetail> getKHHTDetail(String maSo) {
        List<KeHoachHocTapDTO> khhtList = getKeHoachHocTapsByMaSo(maSo);

        List<String> maHocPhanList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocPhan)
                .distinct()
                .toList();
        List<Long> maHocKyList = khhtList.stream()
                .map(KeHoachHocTapDTO::getMaHocKy)
                .distinct()
                .toList();

        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);

        return getKeHoachHocTapDetails(khhtList, hocPhanDTOList, hocKyDTOList);
    }

    public List<KeHoachHocTapDTO> getKeHoachHocTapsByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        List<KeHoachHocTap> keHoachHocTaps = keHoachHocTapRepository.findKeHoachHocTapsByMaSo(maSo);
        if (keHoachHocTaps.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }

        return keHoachHocTaps.stream()
                .map(khht -> modelMapper.map(khht, KeHoachHocTapDTO.class))
                .toList();
    }

    public List<String> getMaHocPhanByMaSo(String maSo) {
        if (maSo == null || maSo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }

        List<String> maHocPhanList = keHoachHocTapRepository.findMaHocPhanByMaSo(maSo);

        if (maHocPhanList.isEmpty()) {
            throw new AppException(ErrorCode.NOTFOUND);
        }
        return maHocPhanList;
    }


    public KeHoachHocTapDTO update(KeHoachHocTapDTO khht) {
        if (khht.getId() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        KeHoachHocTap existingKHHT = keHoachHocTapRepository.findById(khht.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        modelMapper.map(khht, existingKHHT);
        keHoachHocTapRepository.save(existingKHHT);

        return modelMapper.map(existingKHHT, KeHoachHocTapDTO.class);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        KeHoachHocTap existingKHHT = keHoachHocTapRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        keHoachHocTapRepository.delete(existingKHHT);
    }

    private boolean isHocPhanExist(String maHocPhan) {
        try {
            hocPhanClient.getHocPhanById(maHocPhan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isKeHoachHocTapExist(String maSo, String maHocPhan) {
        return keHoachHocTapRepository.findByMaSoAndMaHocPhan(maSo, maHocPhan).isPresent();
    }

    public void creates(List<KeHoachHocTapRequest> request) {
        if(request == null || request.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<KeHoachHocTap> khht = request.stream().map(
                khhtRequest -> modelMapper.map(khhtRequest, KeHoachHocTap.class)).toList();
        keHoachHocTapRepository.saveAll(khht);
    }
}

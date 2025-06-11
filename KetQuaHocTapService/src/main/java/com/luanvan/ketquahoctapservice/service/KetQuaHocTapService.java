package com.luanvan.ketquahoctapservice.service;

import com.luanvan.ketquahoctapservice.Repository.KetQuaHocTapRepository;
import com.luanvan.ketquahoctapservice.Repository.httpClient.HocPhanClient;
import com.luanvan.ketquahoctapservice.entity.KetQuaHocTap;
import com.luanvan.ketquahoctapservice.exception.AppException;
import com.luanvan.ketquahoctapservice.exception.ErrorCode;
import com.luanvan.ketquahoctapservice.model.Response.DiemTrungBinh;
import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import com.luanvan.ketquahoctapservice.model.dto.HocPhanDTO;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDTO;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDetail;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KetQuaHocTapService {
    private final KetQuaHocTapRepository ketQuaHocTapRepository;
    private final ModelMapper modelMapper;
    private final HocPhanClient hocPhanClient;
    private Double calculateDiemTrungBinh(List<KetQuaHocTap> ketQuaHocTapList) {
        if (ketQuaHocTapList == null || ketQuaHocTapList.isEmpty()) {
            return null;
        }
        double diemTB = 0.0;
        long tongSoTinChi = 0;
        for (KetQuaHocTap ketQua : ketQuaHocTapList) {
            if (ketQua != null && ketQua.getDiem4() != null && ketQua.getSoTinChi() != null) {
                if(ketQua.getDiemChu().equals("I") || ketQua.getDiemChu().equals("F")){
                    continue;
                }
                diemTB += ketQua.getDiem4() * ketQua.getSoTinChi();
                tongSoTinChi += ketQua.getSoTinChi();
            }
        }
        return diemTB > 0 ? diemTB / tongSoTinChi : null;
    }

    public List<DiemTrungBinh> getDiemTrungBinhListByMaSo(String maSo) {
        List<KetQuaHocTap> ketQuaHocTap = ketQuaHocTapRepository.findByMaSo(maSo);
        if (ketQuaHocTap == null || ketQuaHocTap.isEmpty()) {
            return null;
        }
        List<Long> hocKyList = ketQuaHocTapRepository.findMaHocKyByMaSo(maSo);
        if (hocKyList == null || hocKyList.isEmpty()) {
            return null;
        }
        List<DiemTrungBinh> diemTrungBinhList = new ArrayList<>();
        for(long hocKy : hocKyList){
            DiemTrungBinh diemTrungBinh = new DiemTrungBinh();
            diemTrungBinh.setDiemTrungBinhTichLuy(getDiemTrungBinhTichLuy(maSo, hocKy));
            diemTrungBinhList.add(diemTrungBinh);
        }
        return diemTrungBinhList;
    }

    public Double getDiemTrungBinhTichLuy(String maSo, Long maHocKy) {
        List<KetQuaHocTap> kqht = ketQuaHocTapRepository.findByMaSoAndMaHocKyIsLessThanEqual(maSo, maHocKy);
        kqht = (kqht == null) ? Collections.emptyList() :
                kqht.stream()
                        .filter(ketQua -> ketQua != null && !ketQua.isDieuKien())
                        .toList();
        return calculateDiemTrungBinh(kqht);
    }

    public Double getDiemTrungBinhHocKy(String maSo, Long maHocKy) {
        List<KetQuaHocTap> kqhtList = ketQuaHocTapRepository.findByMaSoAndMaHocKy(maSo, maHocKy);
        if(kqhtList == null || kqhtList.isEmpty()){
            throw new AppException(ErrorCode.KET_QUA_HOC_TAP_EMPTY);
        }
        return calculateDiemTrungBinh(kqhtList);
    }

    public List<KetQuaHocTapDetail> getKetQuaHocTapDetailList(String maSo){
        List<KetQuaHocTap> kqhtList = ketQuaHocTapRepository.findByMaSo(maSo);
        List<KetQuaHocTapDetail> kqhtDetailList = new LinkedList<>();
        kqhtList.forEach(khhtDetail -> {
            khhtDetail.setMaNhomHP(khhtDetail.getMaNhomHP());
            khhtDetail.setDieuKien(khhtDetail.isDieuKien());
        });

        List<String> maHocPhanList = kqhtList.stream()
                .map(KetQuaHocTap::getMaHp)
                .distinct()
                .toList();
        List<Long> maHocKyList = kqhtList.stream()
                .map(KetQuaHocTap::getMaHocKy)
                .distinct()
                .toList();

        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(maHocKyList);

        Map<String, HocPhanDTO> hocPhanMap = hocPhanDTOList.stream()
                .collect(Collectors.toMap(HocPhanDTO::getMaHp, Function.identity()));
        Map<Long, HocKyDTO> hocKyMap = hocKyDTOList.stream()
                .collect(Collectors.toMap(HocKyDTO::getMaHocKy, Function.identity()));


        kqhtList.forEach(kqht -> {
            HocPhanDTO hocPhanDTO = hocPhanMap.get(kqht.getMaHp());
            HocKyDTO hocKyDTO = hocKyMap.get(kqht.getMaHocKy());

            if (hocPhanDTO != null && hocKyDTO != null) {
                KetQuaHocTapDetail ketQuaHocTapDetail = new KetQuaHocTapDetail();
                ketQuaHocTapDetail.setId(kqht.getId());
                ketQuaHocTapDetail.setHocKyDTO(hocKyDTO);
                ketQuaHocTapDetail.setHocPhanDTO(hocPhanDTO);
                ketQuaHocTapDetail.setSoTinChi(hocPhanDTO.getTinChi());
                ketQuaHocTapDetail.setNamHocDTO(hocKyDTO.getNamHocDTO());
                kqhtDetailList.add(ketQuaHocTapDetail);
            }
        });
        return kqhtDetailList;
    }


    public List<KetQuaHocTapDTO> creates(List<KetQuaHocTapDTO> ketQuaHocTapDTOList) {
        if (ketQuaHocTapDTOList == null || ketQuaHocTapDTOList.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
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

    //    Read from formated file excel
    public void createKHHTByFileExcel(MultipartFile file) {
        List<KetQuaHocTap> DSKhht = new LinkedList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            sheet.forEach(row -> {
                if (row.getRowNum() == 0) return; // Skip header row
                KetQuaHocTap ketQuaHocTap  = new KetQuaHocTap();
                ketQuaHocTap.setDiemChu(getStringCellValue(row.getCell(0)));
                ketQuaHocTap.setDiemSo(row.getCell(1).getNumericCellValue());
                ketQuaHocTap.setDieuKien(getBooleanCellValue(row.getCell(2)));
                ketQuaHocTap.setMaNhomHP((long) row.getCell(3).getNumericCellValue());
                ketQuaHocTap.setMaSo(getStringCellValue(row.getCell(4)));
                ketQuaHocTap.setMaHocKy((long) row.getCell(5).getNumericCellValue());
                ketQuaHocTap.setMaHp(getStringCellValue(row.getCell(6)));
                ketQuaHocTap.setSoTinChi((long) row.getCell(7).getNumericCellValue());
                DSKhht.add(ketQuaHocTap);
            });
            ketQuaHocTapRepository.saveAll(DSKhht);
        } catch (IOException e) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
    }


    private boolean getBooleanCellValue(Cell cell){
        if(cell == null) return false;
        return switch (cell.getCellType()){
            case STRING -> cell.getStringCellValue().equalsIgnoreCase("true");
            case NUMERIC -> cell.getNumericCellValue() > 0;
            default -> false;
        };
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}

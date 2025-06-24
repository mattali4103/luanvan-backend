package com.luanvan.kehoachhoctapservice.service;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTapMau;
import com.luanvan.kehoachhoctapservice.exception.AppException;
import com.luanvan.kehoachhoctapservice.exception.ErrorCode;
import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import com.luanvan.kehoachhoctapservice.model.dto.HocPhanDTO;
import com.luanvan.kehoachhoctapservice.model.dto.KeHoachHocTapMauDTO;
import com.luanvan.kehoachhoctapservice.model.response.KeHoachHocTapDetail;
import com.luanvan.kehoachhoctapservice.repository.KeHoachHocTapMauRepository;
import com.luanvan.kehoachhoctapservice.repository.httpClient.HocPhanClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class KeHoachHocTapMauService {
    private final HocPhanClient hocPhanClient;
    private final KeHoachHocTapMauRepository keHoachHocTapMauRepository;
    ModelMapper modelMapper = new ModelMapper();


    @Transactional
    public void importKeHoachHocTapMau(String khoaHoc, Long maNganh, MultipartFile file) {
        if (khoaHoc == null || maNganh == null || file.isEmpty()) {
            log.error("Invalid request parameters: khoaHoc={}, maNganh={}, file={}", khoaHoc, maNganh, file.isEmpty());
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        log.info("Importing KeHoachHocTapMau for khoaHoc={}, maNganh={}", khoaHoc, maNganh);
        List<KeHoachHocTapMau> khhtList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 11; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(1) == null || row.getCell(4) == null || row.getCell(5) == null) {
                    log.info("Stopping at row {} due to missing required cells", i + 1);
                    break;
                }


                String maHocPhan = formatter.formatCellValue(row.getCell(1)).trim();
                String namHocRaw = formatter.formatCellValue(row.getCell(4)).trim();
                String namHoc = namHocRaw.replaceAll("[\\u00A0\\s]+", " ").trim();
                String hocKy = formatter.formatCellValue(row.getCell(5)).trim();

                log.debug("Row {}: maHocPhan={}, namHoc={}, hocKy={}", i + 1, maHocPhan, namHoc, hocKy);

                // Skip footer or invalid rows
                if (maHocPhan.isEmpty() || namHoc.isEmpty() || hocKy.isEmpty()) {
                    log.info("Skipping row {}: empty or invalid data", i + 1);
                    break;
                }

                String[] namHocPart = namHoc.split("-");
                String namHocPhan = namHocPart[0];
                String namKetThuc = namHocPart[1];

                String cacheKey = namHocPhan + "-" + namKetThuc + "-" + hocKy;
                HocKyDTO dto = hocPhanClient.findHocKyByName(namHocPhan, namKetThuc, hocKy);

                if (dto == null) {
                    log.warn("Học kỳ không tồn tại: {} tại dòng {}", cacheKey, i + 1);
                    continue;
                }

                KeHoachHocTapMau keHoachHocTapMau = new KeHoachHocTapMau();
                keHoachHocTapMau.setMaHocPhan(maHocPhan);
                keHoachHocTapMau.setKhoaHoc(khoaHoc);
                keHoachHocTapMau.setMaNganh(maNganh);
                keHoachHocTapMau.setMaHocKy(dto.getMaHocKy());
                log.info("Adding KeHoachHocTapMau: maHocPhan={}, khoaHoc={}, maNganh={}, maHocKy={}",
                        maHocPhan, khoaHoc, maNganh, dto.getMaHocKy());
                khhtList.add(keHoachHocTapMau);
            }
        } catch (IOException e) {
            log.error("Failed to parse Excel file", e);
            throw new AppException(ErrorCode.FILE_PARSING_ERROR);
        }

        if (khhtList.isEmpty()) {
            throw new AppException(ErrorCode.LIST_EMPTY);
        }
        log.info("Total KeHoachHocTapMau to save: {}", khhtList.size());
        keHoachHocTapMauRepository.saveAll(khhtList);
    }

    public List<HocKyDTO> getHocKyInKHHTMau(String khoaHoc, Long maNganh) {
        List<KeHoachHocTapMau> list = keHoachHocTapMauRepository.findByKhoaHocAndMaNganh(khoaHoc, maNganh);
        if(list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> hocKyList = list.stream().map(KeHoachHocTapMau::getMaHocKy).distinct().toList();
        return hocPhanClient.getHocKyIn(hocKyList);
    }



    public List<KeHoachHocTapDetail> getKeHoachHocTapMau(String khoaHoc, Long maNganh, Long maHocKy) {
        List<KeHoachHocTapMau> list = keHoachHocTapMauRepository.findByKhoaHocAndMaNganhAndMaHocKy(khoaHoc, maNganh, maHocKy);
        if(list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> hocKyList = list.stream().map(KeHoachHocTapMau::getMaHocKy).toList();
        List<String> maHocPhanList = list.stream().map(KeHoachHocTapMau::getMaHocPhan).toList();
        List<HocPhanDTO> hocPhanDTOList = hocPhanClient.getHocPhanIn(maHocPhanList);
        List<HocKyDTO> hocKyDTOList = hocPhanClient.getHocKyIn(hocKyList);
        List<KeHoachHocTapDetail> result = new LinkedList<>();
        Map<String, HocPhanDTO> hocPhanMap = hocPhanDTOList.stream()
                .collect(Collectors.toMap(HocPhanDTO::getMaHp, hp -> hp));
        Map<Long, HocKyDTO> hocKyMap = hocKyDTOList.stream()
                .collect(Collectors.toMap(HocKyDTO::getMaHocKy, hk -> hk));
        list.forEach(keHoachHocTapMau -> {
            HocPhanDTO hocPhanDTO = hocPhanMap.get(keHoachHocTapMau.getMaHocPhan());
            HocKyDTO hocKyDTO = hocKyMap.get(keHoachHocTapMau.getMaHocKy());
            if (hocPhanDTO != null && hocKyDTO != null) {
                KeHoachHocTapDetail detail = KeHoachHocTapDetail.builder()
                        .id(keHoachHocTapMau.getId())
                        .hocPhan(hocPhanDTO)
                        .hocKy(hocKyDTO)
                        .build();
                result.add(detail);
            }
        });
        return result;
    }


    @Transactional
    public KeHoachHocTapMauDTO create(KeHoachHocTapMau keHoachHocTapMau) {
        if (keHoachHocTapMau.getKhoaHoc() == null || keHoachHocTapMau.getMaNganh() == null) {
            throw new IllegalArgumentException("MaKhoa and MaNganh cannot be null");
        }
        if (keHoachHocTapMauRepository.existsByKhoaHocAndMaNganh(keHoachHocTapMau.getKhoaHoc(), keHoachHocTapMau.getMaNganh())) {
            throw new IllegalArgumentException("Ke Hoach Hoc Tap Mau already exists for the given Khoa Hoc and Ma Nganh");
        }
        //        Kiểm tra học phần này con tồn tại trong hệ thống không
        if (isHocPhanExist(keHoachHocTapMau.getMaHocPhan())) {
            throw new AppException(ErrorCode.HOCPHAN_NOTFOUND);
        }
        KeHoachHocTapMau savedKeHoachHocTapMau = keHoachHocTapMauRepository.save(keHoachHocTapMau);
        return modelMapper.map(savedKeHoachHocTapMau, KeHoachHocTapMauDTO.class);
    }


    private boolean isHocPhanExist(String maHocPhan) {
        try {
            hocPhanClient.getHocPhanById(maHocPhan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

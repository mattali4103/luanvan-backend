package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.HocPhan;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.HocPhanDTO;

import com.luanvan.hocphanservice.repository.HocPhanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HocPhanService {
    private final HocPhanRepository hocPhanRepository;
    private final ModelMapper modelMapper;

    private final HocPhanTuChonService hocPhanTuChonService;


    public List<HocPhanDTO> getAll(){
        List<HocPhan> hocPhanList = hocPhanRepository.findAll();
        return hocPhanList.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }


    public List<HocPhanDTO> getDSHocPhanIn(List<String> maHocPhanList) {
        if (maHocPhanList.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<HocPhan> hocPhanList = hocPhanRepository.findByMaHpIn(maHocPhanList);
        return hocPhanList.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }

    public List<HocPhanDTO> getDsHocPhan() {
        List<HocPhan> hocPhanList = hocPhanRepository.findAll();
        return hocPhanList.stream().map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class)).collect(Collectors.toList());
    }

    public HocPhanDTO getDsHocPhanById(String maHp) {
        HocPhan hocPhan = hocPhanRepository.findById(maHp)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return modelMapper.map(hocPhan, HocPhanDTO.class);
    }

    //    Yêu cầu NhomHP not null
    public HocPhanDTO createHocPhan(HocPhanDTO hocPhanDTO) {
        ModelMapper modelMapper = new ModelMapper();
        if (hocPhanRepository.findByTenHp(hocPhanDTO.getTenHp()).isPresent()) {
            throw new AppException(ErrorCode.EXISTED);
        }

        modelMapper.typeMap(HocPhanDTO.class, HocPhan.class).addMappings(
                        mapper -> mapper.skip(HocPhan::setDsNhomHp))
                .addMappings(mapper -> mapper.skip(HocPhan::setChuongTrinhDaoTaoList))
        ;
        HocPhan hocPhan = modelMapper.map(hocPhanDTO, HocPhan.class);
        hocPhanRepository.save(hocPhan);
        return modelMapper.map(hocPhan, HocPhanDTO.class);
    }

    //    Nhóm HP có thể null
    public List<HocPhanDTO> createDSHocPhan(List<HocPhanDTO> DSHocPhanDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.typeMap(HocPhanDTO.class, HocPhan.class).addMappings(mapper -> mapper.skip(HocPhan::setDsNhomHp))
        .addMappings(mapper -> mapper.skip(HocPhan::setChuongTrinhDaoTaoList));
        List<HocPhan> DSHocPhan = DSHocPhanDTO.stream().map(hocPhanDTO -> modelMapper.map(hocPhanDTO, HocPhan.class)).toList();

        hocPhanRepository.saveAll(DSHocPhan);
        return DSHocPhan.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }

    //    Read from formated file excel
    public void createDSHocPhanFromFile(MultipartFile file) {
        List<HocPhan> DSHocPhan = new LinkedList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            sheet.forEach(row -> {
                if (row.getRowNum() == 0) return; // Skip header row
                HocPhan hocPhan = new HocPhan();
                hocPhan.setMaHp(getStringCellValue(row.getCell(0)));
                hocPhan.setTenHp(getStringCellValue(row.getCell(1)));
                hocPhan.setTinChi((int) row.getCell(2).getNumericCellValue());
                hocPhan.setLoaiHp(getStringCellValue(row.getCell(3)));
                hocPhan.setHocPhanTienQuyet(getStringCellValue(row.getCell(4)));
                hocPhan.setMoTa(getStringCellValue(row.getCell(5)));
                DSHocPhan.add(hocPhan);
            });
            hocPhanRepository.saveAll(DSHocPhan);
        } catch (IOException e) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
    }

//    Thấy học phần nằm trong CTDT dựa theo loại học phần và  khoá học
    public List<HocPhanDTO> getHocPhanInCTDTByLoaiHp(String loaiHp, String khoaHoc, Long maNganh) {
        if(loaiHp == null || khoaHoc == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<HocPhan> hocPhanList = hocPhanRepository.findHocPhanByLoaiHpInChuongTrinhDaoTao(loaiHp, khoaHoc, maNganh);
        if(hocPhanList.isEmpty()){
            return new ArrayList<>();
        }

        List<HocPhanDTO> result = hocPhanList.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());

        if(loaiHp.equals("Cơ sở Ngành")){
            List<HocPhanDTO> dtoList = hocPhanTuChonService.getNhomHocPhanTheChat();
            result.addAll(dtoList);
        }

        return result;
    }


//    Lấy học phần không có trong không có trong danh sách
    @SuppressWarnings("unused")
    public List<HocPhanDTO> getHocPhanNotInList(List<String> maHocPhanList) {
        if(maHocPhanList.isEmpty()){
            List<HocPhan> hocPhanList = hocPhanRepository.findAll();
            return hocPhanList.stream().map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class)).collect(Collectors.toList());
        }
        List<HocPhan> hocPhanList = hocPhanRepository.findByMaHpNotIn(maHocPhanList);
        return hocPhanList.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }

    protected String getStringCellValue(Cell cell) {
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

    public Long getCountByMaHocPhanIn(List<String> maHocPhanList) {
        validate(Collections.singletonList(maHocPhanList));
        return hocPhanRepository.countTinChiIn(maHocPhanList);
    }

    private void validate(List<Object> list){
        if(list.isEmpty()){
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
    }

    public List<HocPhanDTO> getHocPhanQuocPhong() {
        List<HocPhan> hocPhanList = hocPhanRepository.findByTenHpLike(("Quốc phòng"));
        if (hocPhanList.isEmpty()) {
            return new ArrayList<>();
        }
        return hocPhanList.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }

    public List<HocPhanDTO> getHocPhanByLoaiHp(String loaiHocPhan) {
        if (loaiHocPhan == null || loaiHocPhan.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        List<HocPhan> hocPhanList = hocPhanRepository.findByLoaiHpLike(loaiHocPhan);

        if (hocPhanList.isEmpty()) {
            return new ArrayList<>();
        }
        return hocPhanList.stream()
                .map(hocPhan -> modelMapper.map(hocPhan, HocPhanDTO.class))
                .collect(Collectors.toList());
    }
}

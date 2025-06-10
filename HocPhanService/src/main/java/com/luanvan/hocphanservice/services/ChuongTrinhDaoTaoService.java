package com.luanvan.hocphanservice.services;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import com.luanvan.hocphanservice.entity.HocPhan;
import com.luanvan.hocphanservice.exception.AppException;
import com.luanvan.hocphanservice.exception.ErrorCode;
import com.luanvan.hocphanservice.model.ChuongTrinhDaoTaoDTO;
import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.Request.CTDTDescriptionRequest;
import com.luanvan.hocphanservice.repository.ChuongTrinhDaoTaoRepository;
import com.luanvan.hocphanservice.repository.HocPhanRepository;
import com.luanvan.hocphanservice.repository.httpClient.NganhClient;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChuongTrinhDaoTaoService {

    private final ModelMapper modelMapper;
    private final ChuongTrinhDaoTaoRepository chuongTrinhDaoTaoRepository;
    private final HocPhanRepository hocPhanRepository;
    private final NganhClient nganhClient;

    public ChuongTrinhDaoTaoDTO getCTDTByMaNganh(Long maNganh) {
        if (maNganh == null) {
            throw new AppException("MaNganh cannot be null", ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findByMaNganh(maNganh)
                .orElseThrow(() -> new AppException("Không tìm thấy ngành trong csdl",ErrorCode.NOTFOUND));

        return modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoDTO.class);
    }

    public ChuongTrinhDaoTaoDTO create(ChuongTrinhDaoTaoDTO dto){
        if(dto == null){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        if(!nganhClient.existByMaNganh(dto.getMaNganh())){
            throw new AppException(ErrorCode.NOTFOUND);
        }


        modelMapper.typeMap(ChuongTrinhDaoTaoDTO.class, ChuongTrinhDaoTao.class)
                .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList))
        .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setId));
        ChuongTrinhDaoTao chuongTrinhDaoTao = modelMapper.map(dto, ChuongTrinhDaoTao.class);


        List<String> maHocPhanList = dto.getHocPhanList().stream()
                .map(HocPhanDTO::getMaHp)
                .toList();

        List<HocPhan> hocPhanList = hocPhanRepository.findByMaHpIn(maHocPhanList);

        if(hocPhanList.isEmpty()){
            throw new AppException(ErrorCode.NOTFOUND);
        }



        chuongTrinhDaoTao.setHocPhanList(hocPhanList);
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        return modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoDTO.class);
    }



    public ChuongTrinhDaoTaoDTO update(ChuongTrinhDaoTaoDTO dto) {
        ModelMapper updateMapper = new ModelMapper();

        if (dto == null || dto.getMaNganh() == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        updateMapper.getConfiguration().setSkipNullEnabled(true);
        updateMapper.typeMap(ChuongTrinhDaoTaoDTO.class, ChuongTrinhDaoTao.class)
                .addMappings(mapper -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList));
        ChuongTrinhDaoTao chuongTrinhDaoTao = updateMapper.map(dto, ChuongTrinhDaoTao.class);
        chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        return updateMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoDTO.class);
    }
    public ChuongTrinhDaoTaoDTO getById(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        return chuongTrinhDaoTaoRepository.findById(id)
                .map(chuongTrinhDaoTao -> modelMapper.map(chuongTrinhDaoTao, ChuongTrinhDaoTaoDTO.class))
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
    }
    public void deleteById(Long id) {
        if (id == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        chuongTrinhDaoTaoRepository.delete(chuongTrinhDaoTao);
    }


    @Transactional
    public void createDSHocPhanFromFile(CTDTDescriptionRequest request, MultipartFile file) {
        if(request == null || file == null || file.isEmpty() ){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        if(request.getMaNganh() == null || request.getKhoaHoc() == null){
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        boolean nganhExists = nganhClient.existByMaNganh(request.getMaNganh());
        if(!nganhExists){
            throw new AppException(ErrorCode.NOTFOUND);
        }

        try{
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<String> maHocPhanList = new LinkedList<>();
            sheet.forEach(row -> {
                if(row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
                    return;
                }
                String maHocPhan = row.getCell(0).getStringCellValue();
                maHocPhanList.add(maHocPhan);
            });
            List<HocPhan> chuongTrinhList = hocPhanRepository.findByMaHpIn(maHocPhanList);
            if(chuongTrinhList.isEmpty()){
                throw new AppException(ErrorCode.NOTFOUND);
            }
            modelMapper.typeMap(CTDTDescriptionRequest.class, ChuongTrinhDaoTao.class)
                    .addMappings(mapper
                            -> mapper.skip(ChuongTrinhDaoTao::setHocPhanList));
            ChuongTrinhDaoTao chuongTrinhDaoTao = modelMapper.map(request, ChuongTrinhDaoTao.class);
            chuongTrinhDaoTao.setHocPhanList(chuongTrinhList);
            chuongTrinhDaoTaoRepository.save(chuongTrinhDaoTao);
        }catch(IOException e){
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
    }
}

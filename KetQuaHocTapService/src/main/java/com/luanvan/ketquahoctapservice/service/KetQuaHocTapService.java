package com.luanvan.ketquahoctapservice.service;

import com.luanvan.ketquahoctapservice.Repository.KetQuaHocTapRepository;
import com.luanvan.ketquahoctapservice.entity.KetQuaHocTap;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KetQuaHocTapService {
    private final KetQuaHocTapRepository ketQuaHocTapRepository;

    public List<KetQuaHocTapDTO> findBySinhVienId(Long sinhvienId) {
        List<KetQuaHocTap> ketQuaHocTapList = ketQuaHocTapRepository.findKetQuaHocTapsBySinhVienId(sinhvienId);
        List<KetQuaHocTapDTO> ketQuaHocTapDTOList = new ArrayList<>();
        ketQuaHocTapList.forEach(item -> {
            KetQuaHocTapDTO ketQuaHocTapDTO = new KetQuaHocTapDTO();
            ketQuaHocTapDTO.setId(item.getId());
            ketQuaHocTapDTO.setDiem_chu(item.getDiem_chu());
            ketQuaHocTapDTO.setDiem_so(item.getDiem_so());
            ketQuaHocTapDTO.setMa_hoc_phan(item.getMa_hoc_phan());
            ketQuaHocTapDTO.setSinhVienId(item.getSinhVienId());
            ketQuaHocTapDTO.setMa_hoc_ky(item.getMa_hoc_ky());
            ketQuaHocTapDTO.setDieu_kien(item.getDieu_kien());
            ketQuaHocTapDTO.setMaNhomHP(item.getMaNhomHP());
            ketQuaHocTapDTOList.add(ketQuaHocTapDTO);
        });
        return ketQuaHocTapDTOList;
    }
}

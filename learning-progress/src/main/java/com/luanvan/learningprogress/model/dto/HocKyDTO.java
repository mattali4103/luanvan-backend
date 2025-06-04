package com.luanvan.learningprogress.model.dto;

import com.luanvan.learningprogress.entity.KeHoachHocTap;
import com.luanvan.learningprogress.entity.KetQuaHocTap;
import com.luanvan.learningprogress.entity.NamHoc;
import com.luanvan.learningprogress.entity.NhomHP;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HocKyDTO {
    String ma_hoc_ky;
    LocalDate ngay_bat_dau_hoc;
    LocalDate ngay_ket_thuc;

    NamHoc nam_hoc;

    List<KeHoachHocTapDTO> ds_ke_hoach_hoc_tap;

    List<KetQuaHocTapDTO> ds_ket_qua_hoc_tap;

    List<NhomHPDTO> ds_nhom_hp;
}

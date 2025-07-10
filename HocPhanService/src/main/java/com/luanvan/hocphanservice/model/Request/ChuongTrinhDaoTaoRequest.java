package com.luanvan.hocphanservice.model.Request;

import com.luanvan.hocphanservice.model.HocPhanDTO;
import com.luanvan.hocphanservice.model.HocPhanTuChonDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuongTrinhDaoTaoRequest {
    Long id;
    String tenChuongTrinhDaoTao;

    @NotBlank(message = "Mã ngành không được null")
    String khoaHoc;
    @NotBlank(message = "Mã ngành không được null")
    Long maNganh;
    String noiDung;
    List<HocPhanDTO> hocPhanList;
    List<HocPhanTuChonDTO> nhomHocPhanTuChon;
    Long tongSoTinChi;
    Long tongSoTinChiTuChon;
}

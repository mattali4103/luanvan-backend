package com.luanvan.kehoachhoctapservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luanvan.kehoachhoctapservice.model.dto.HocKyDTO;
import com.luanvan.kehoachhoctapservice.model.dto.HocPhanDTO;
import com.luanvan.kehoachhoctapservice.model.dto.NamHocDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeHoachHocTapDetail {
    Long id;
    String loaiHocPhan;
    HocPhanDTO hocPhan;
    HocKyDTO hocKy;
    NamHocDTO namHoc;
    boolean hocPhanCaiThien;
}

package com.luanvan.hocphanservice.model;


import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
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
public class ChuongTrinhDaoTaoDTO {
    @NotBlank(message = "Mã ngành không được null")
    String khoaHoc;
    @NotBlank(message = "Mã ngành không được null")
    Long maNganh;
    String noiDung;
    List<HocPhanDTO> hocPhanList;

}

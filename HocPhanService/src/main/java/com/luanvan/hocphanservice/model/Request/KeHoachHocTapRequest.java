package com.luanvan.hocphanservice.model.Request;

import com.luanvan.hocphanservice.model.HocPhanDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeHoachHocTapRequest {
    String maSo;
    String maHocPhan;
    Long maHocKy;
    boolean hocPhanCaiThien;
}

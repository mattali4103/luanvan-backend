package com.luanvan.ketquahoctapservice.model.Response;

import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDetail;
import com.luanvan.ketquahoctapservice.model.dto.NamHocDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KetQuaHocTapByNamHoc {
    NamHocDTO namHoc;
    List<KetQuaHocTapDetail> ketQuaHocTapList;
    Double diemTrungBinhHocKy;
    Double diemTrungBinhTichLuy;
}

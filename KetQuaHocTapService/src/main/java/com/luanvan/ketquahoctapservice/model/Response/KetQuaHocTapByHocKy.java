package com.luanvan.ketquahoctapservice.model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import com.luanvan.ketquahoctapservice.model.dto.KetQuaHocTapDetail;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KetQuaHocTapByHocKy {
    HocKyDTO hocKy;
    List<KetQuaHocTapDetail> ketQuaHocTapList;
    Double diemTrungBinhHocKy;
    Double diemTrungBinhTichLuy;
}

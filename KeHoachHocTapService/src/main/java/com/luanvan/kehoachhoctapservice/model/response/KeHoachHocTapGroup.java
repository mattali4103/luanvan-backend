package com.luanvan.kehoachhoctapservice.model.response;

import com.luanvan.kehoachhoctapservice.model.dto.NganhDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class KeHoachHocTapGroup {
    String khoaHoc;
    NganhDTO nganhDTO;
    List<KeHoachHocTapDetail> keHoachHocTapDetailList;
}

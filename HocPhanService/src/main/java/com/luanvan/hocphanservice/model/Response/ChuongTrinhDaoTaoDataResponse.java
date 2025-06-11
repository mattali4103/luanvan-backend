package com.luanvan.hocphanservice.model.Response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ChuongTrinhDaoTaoDataResponse {
    String khoaHoc;
    Long maNganh;
    List<HocPhanDataResponse> hocPhanList;
}

package com.luanvan.ketquahoctapservice.model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luanvan.ketquahoctapservice.model.dto.HocKyDTO;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiemTrungBinh {
    HocKyDTO hocKy;
    Double DiemTrungBinh;
    Double DiemTrungBinhTichLuy;
}

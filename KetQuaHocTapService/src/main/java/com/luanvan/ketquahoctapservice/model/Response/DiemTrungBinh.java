package com.luanvan.ketquahoctapservice.model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiemTrungBinh {
    Long maHocKy;
    Double DiemTrungBinh;
    Double DiemTrungBinhTichLuy;
}

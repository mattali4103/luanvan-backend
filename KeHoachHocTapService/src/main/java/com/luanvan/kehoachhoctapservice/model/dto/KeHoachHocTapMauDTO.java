package com.luanvan.kehoachhoctapservice.model.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class KeHoachHocTapMauDTO{
    Long id;
    String khoaHoc;
    Long maNganh;
    Long maHocKy;
    String maHocPhan;
}
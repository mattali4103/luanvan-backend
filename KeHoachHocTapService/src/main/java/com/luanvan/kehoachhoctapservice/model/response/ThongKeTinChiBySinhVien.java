package com.luanvan.kehoachhoctapservice.model.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTinChiBySinhVien {
    String maSo;
    List<ThongKeTinChi> thongKeTinChiList;
}
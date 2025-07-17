package com.luanvan.ketquahoctapservice.model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CanhBaoHocVu {
    String maSo;
    String lyDo;
}

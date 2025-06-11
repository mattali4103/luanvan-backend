package com.luanvan.userservice.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {
    String maSo;
    String password;
    List<RoleDTO> roles;
}

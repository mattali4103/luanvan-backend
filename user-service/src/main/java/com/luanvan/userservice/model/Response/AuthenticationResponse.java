package com.luanvan.userservice.model.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationResponse {
    String token;
    Date expiryDate;
    boolean idValid;
}

package com.apps.quantity_measurement_app.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthorizationResponseDTO  {

    private String name;
    private String email;
    private String token;
}

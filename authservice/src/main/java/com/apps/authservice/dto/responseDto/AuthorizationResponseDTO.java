package com.apps.authservice.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthorizationResponseDTO  {

    private String name;
    private String email;
    private String token;
}
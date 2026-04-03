package com.apps.authservice.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    
    @NotNull(message="Email cannot be null")
    private String email;
    
    @NotNull(message="Password cannot be null")
    private String password;
    
}
package com.apps.authservice.service;

import com.apps.authservice.dto.requestDto.LoginRequestDTO;
import com.apps.authservice.dto.requestDto.RegisterRequestDTO;
import com.apps.authservice.dto.responseDto.AuthorizationResponseDTO;
import com.apps.authservice.exception.InvalidUserCredentialsException;
import com.apps.authservice.exception.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    
    AuthorizationResponseDTO register(RegisterRequestDTO registerRequestDTO) throws UserAlreadyExistsException, InvalidUserCredentialsException ;

    AuthorizationResponseDTO login(LoginRequestDTO loginRequestDTO) throws InvalidUserCredentialsException;

    String logout(HttpServletRequest request);
}

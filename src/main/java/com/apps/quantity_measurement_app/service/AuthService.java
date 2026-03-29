package com.apps.quantity_measurement_app.service;

import com.apps.quantity_measurement_app.dto.requestDto.LoginRequestDTO;
import com.apps.quantity_measurement_app.dto.requestDto.RegisterRequestDTO;
import com.apps.quantity_measurement_app.dto.responseDto.AuthorizationResponseDTO;
import com.apps.quantity_measurement_app.exception.InvalidUserCredentialsException;
import com.apps.quantity_measurement_app.exception.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    
    AuthorizationResponseDTO register(RegisterRequestDTO registerRequestDTO) throws UserAlreadyExistsException, InvalidUserCredentialsException ;

    AuthorizationResponseDTO login(LoginRequestDTO loginRequestDTO) throws InvalidUserCredentialsException;

    String logout(HttpServletRequest request);
}

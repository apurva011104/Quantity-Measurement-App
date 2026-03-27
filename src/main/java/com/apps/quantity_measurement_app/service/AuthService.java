package com.apps.quantity_measurement_app.service;

import com.apps.quantity_measurement_app.dto.requestDto.LoginRequestDTO;
import com.apps.quantity_measurement_app.dto.requestDto.RegisterRequestDTO;
import com.apps.quantity_measurement_app.exception.InvalidUserCredentialsException;
import com.apps.quantity_measurement_app.exception.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    
    String register(RegisterRequestDTO registerRequestDTO) throws UserAlreadyExistsException, InvalidUserCredentialsException ;

    String login(LoginRequestDTO loginRequestDTO) throws InvalidUserCredentialsException;

    String logout(HttpServletRequest request);
}

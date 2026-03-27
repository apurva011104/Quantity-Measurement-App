package com.apps.quantity_measurement_app.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.quantity_measurement_app.dto.requestDto.LoginRequestDTO;
import com.apps.quantity_measurement_app.dto.requestDto.RegisterRequestDTO;
import com.apps.quantity_measurement_app.exception.InvalidUserCredentialsException;
import com.apps.quantity_measurement_app.exception.UserAlreadyExistsException;
import com.apps.quantity_measurement_app.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService =authService;
    }

    @GetMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) throws UserAlreadyExistsException, InvalidUserCredentialsException {
        return authService.register(registerRequestDTO);
    }
    
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws InvalidUserCredentialsException{
        return authService.login(loginRequestDTO);
    }
    
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        return authService.logout(request);
    }
}

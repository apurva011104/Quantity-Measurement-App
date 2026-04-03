package com.apps.authservice.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.authservice.dto.requestDto.LoginRequestDTO;
import com.apps.authservice.dto.requestDto.RegisterRequestDTO;
import com.apps.authservice.dto.responseDto.AuthorizationResponseDTO;
import com.apps.authservice.exception.InvalidUserCredentialsException;
import com.apps.authservice.exception.UserAlreadyExistsException;
import com.apps.authservice.service.AuthService;

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
    public ResponseEntity<AuthorizationResponseDTO>  register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) throws UserAlreadyExistsException, InvalidUserCredentialsException {
        AuthorizationResponseDTO response = authService.register(registerRequestDTO);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws InvalidUserCredentialsException{
        AuthorizationResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        return authService.logout(request);
    }
}

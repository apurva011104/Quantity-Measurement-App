package com.apps.quantity_measurement_app.service.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apps.quantity_measurement_app.dto.requestDto.LoginRequestDTO;
import com.apps.quantity_measurement_app.dto.requestDto.RegisterRequestDTO;
import com.apps.quantity_measurement_app.entity.User;
import com.apps.quantity_measurement_app.exception.InvalidUserCredentialsException;
import com.apps.quantity_measurement_app.exception.UserAlreadyExistsException;
import com.apps.quantity_measurement_app.repository.UserRepository;
import com.apps.quantity_measurement_app.security.JwtUtil;
import com.apps.quantity_measurement_app.security.TokenBlacklist;
import com.apps.quantity_measurement_app.service.AuthService;
import com.apps.quantity_measurement_app.util.AuthProvider;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final TokenBlacklist tokenBlacklist;

    @Override
    public String register(RegisterRequestDTO registerRequestDTO) throws UserAlreadyExistsException, InvalidUserCredentialsException {
        
        String email = registerRequestDTO.getEmail();
        String name = registerRequestDTO.getName();
        String password = registerRequestDTO.getPassword();

        if(userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException("User already exists with this email");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setProvider(AuthProvider.LOCAL);

        userRepository.save(user);

        return jwtUtil.generateToken(email);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) throws InvalidUserCredentialsException {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                                    .orElseThrow(()->new InvalidUserCredentialsException("Invalid user credentials"));
        
        if(user.getProvider()!=AuthProvider.LOCAL){
            throw new InvalidUserCredentialsException("Please login using google");
        }
        if(user.getPassword().isEmpty() || !passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new InvalidUserCredentialsException("Invalid user credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    @Override 
    public String logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklist.blacklistToken(token);
        }

        return "Logged out successfully";

    }
}

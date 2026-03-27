package com.apps.quantity_measurement_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apps.quantity_measurement_app.repository.UserRepository;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final UserRepository userRepository;
    private final TokenBlacklist tokenBlacklist;

    public SecurityConfig(JwtUtil jwtUtil,
                        OAuth2SuccessHandler oAuth2SuccessHandler,
                        UserRepository userRepository,
                        TokenBlacklist tokenBlacklist) {
        this.jwtUtil = jwtUtil;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
        this.userRepository = userRepository;
        this.tokenBlacklist = tokenBlacklist;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/error", "/auth/**", "/oauth2/**", "/login/**", "/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            )
            .oauth2Login(oauth -> oauth.successHandler(oAuth2SuccessHandler))
            .addFilterBefore(
                new JwtAuthenticationFilter(jwtUtil, userRepository, tokenBlacklist),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
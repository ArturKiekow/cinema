package com.arturFerreira.cinema.services;

import com.arturFerreira.cinema.controller.dto.LoginRequestDto;
import com.arturFerreira.cinema.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(LoginRequestDto loginDto) {
        var user = userRepository.findByUsername(loginDto.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginDto, passwordEncoder)) {
            throw new BadCredentialsException("username or password is incorrect");
        }

        return jwtService.generateToken(user.get());
    }

}

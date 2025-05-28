package com.arturFerreira.cinema.controller;

import com.arturFerreira.cinema.controller.dto.LoginRequestDto;
import com.arturFerreira.cinema.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequestDto loginDto) {
        return authenticationService.authenticate(loginDto);
    }

}

package com.arturFerreira.cinema.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        @CPF
        String cpf,
        LocalDate birthDate,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password) {
}

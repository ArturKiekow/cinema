package com.arturFerreira.cinema.controller.dto;

import com.arturFerreira.cinema.entity.Role;
import com.arturFerreira.cinema.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Set;

public record CreateUserResponseDto(
        String username,
        String cpf,
        LocalDate birthDate,
        String email,
        Set<Role> roles) { //RETORNAR DTO DE ROLES

    public static CreateUserResponseDto fromEntity(User user){
        return new CreateUserResponseDto(user.getUsername(), user.getCpf(), user.getBirthDate(), user.getEmail(), user.getRoles());
    }

}

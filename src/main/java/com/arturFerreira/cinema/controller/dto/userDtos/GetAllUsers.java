package com.arturFerreira.cinema.controller.dto.userDtos;
import com.arturFerreira.cinema.entity.Role;
import com.arturFerreira.cinema.entity.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record GetAllUsers(
        UUID id,
        String username,
        String cpf,
        LocalDate birthDate,
        String email,
        Set<String> roles
) {

    public static GetAllUsers fromClass(User user) {
        return new GetAllUsers(
                user.getId(),
                user.getUsername(),
                user.getCpf(),
                user.getBirthDate(),
                user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toSet())
        );
    }
}

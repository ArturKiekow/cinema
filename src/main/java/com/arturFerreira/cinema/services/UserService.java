package com.arturFerreira.cinema.services;

import com.arturFerreira.cinema.controller.dto.CreateUserDto;
import com.arturFerreira.cinema.entity.Role;
import com.arturFerreira.cinema.entity.User;
import com.arturFerreira.cinema.repository.RoleRepository;
import com.arturFerreira.cinema.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser (CreateUserDto dto){
        var basicRole = roleRepository.findByRoleName(Role.Values.BASIC.name());

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User newUser = new User();

        newUser.setUsername(dto.username());
        newUser.setCpf(dto.cpf());
        newUser.setBirthDate(dto.birthDate());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRoles(Set.of(basicRole));

        return userRepository.save(newUser);


    }


}

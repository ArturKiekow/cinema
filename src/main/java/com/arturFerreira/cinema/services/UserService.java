package com.arturFerreira.cinema.services;

import com.arturFerreira.cinema.controller.dto.userDtos.CreateUserDto;
import com.arturFerreira.cinema.controller.dto.userDtos.UpdateUserDto;
import com.arturFerreira.cinema.entity.Role;
import com.arturFerreira.cinema.entity.User;
import com.arturFerreira.cinema.exceptions.CinemaException;
import com.arturFerreira.cinema.exceptions.UserNotFoundException;
import com.arturFerreira.cinema.exceptions.UsernameOrEmailAlreadyExistsException;
import com.arturFerreira.cinema.repository.RoleRepository;
import com.arturFerreira.cinema.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public User updateUserById(UUID id, UpdateUserDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        System.out.println(jwt.getClaim("scope").toString());

        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (!(user.getUsername().equals(jwt.getSubject()) || jwt.getClaim("scope").equals(Role.Values.ADMIN.name()))) {
            throw new CinemaException("Você não pode modificar uma conta que não é sua");
        }

        if (!Objects.equals(user.getUsername(), dto.username()) && userRepository.findByUsername(dto.username()).isPresent()){
            throw new UsernameOrEmailAlreadyExistsException("Username: " + dto.username() + " já está em uso");
        }
        if (!Objects.equals(user.getEmail(), dto.email()) && userRepository.findByEmail(dto.email()).isPresent()){
            throw new UsernameOrEmailAlreadyExistsException("Email: " + dto.email() + " já está em uso");
        }
        user.setUsername(dto.username());
        user.setBirthDate(dto.birthDate());
        user.setEmail(dto.email());

        return userRepository.save(user);
    }

    public void deleteById(UUID id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        if (!(Objects.equals(user.getUsername(), jwt.getSubject()) || jwt.getClaim("scope").equals(Role.Values.ADMIN.name()))) {
            throw new CinemaException("Você não pode excluir uma conta que não é sua");
        }

        userRepository.deleteById(id);
    }
}

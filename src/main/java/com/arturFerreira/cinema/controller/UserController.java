package com.arturFerreira.cinema.controller;

import com.arturFerreira.cinema.controller.dto.userDtos.CreateUserDto;
import com.arturFerreira.cinema.controller.dto.CreateUserResponseDto;
import com.arturFerreira.cinema.controller.dto.userDtos.GetAllUsers;
import com.arturFerreira.cinema.controller.dto.userDtos.UpdateUserDto;
import com.arturFerreira.cinema.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserDto dto) {
        var user = userService.createUser(dto);
        System.out.println(user.getId());
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(CreateUserResponseDto.fromEntity(user));
    }

    @GetMapping
    public ResponseEntity<List<GetAllUsers>> getAllUsers() {
        var users = userService.getAllUsers()
                .stream()
                .map(GetAllUsers::fromClass)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAllUsers> getUserById(@PathVariable UUID id) {
        var user = userService.getUserById(id);
        return ResponseEntity.ok(GetAllUsers.fromClass(user));
    }

    @GetMapping("/search")
    public ResponseEntity<GetAllUsers> getUserByUsername(@RequestParam String username) {
        var user = userService.getUserByUsername(username);
        return ResponseEntity.ok(GetAllUsers.fromClass(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetAllUsers> updateUserById(@PathVariable UUID id, @RequestBody @Valid UpdateUserDto dto) {
        var user = userService.updateUserById(id, dto);
        return ResponseEntity.ok(GetAllUsers.fromClass(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

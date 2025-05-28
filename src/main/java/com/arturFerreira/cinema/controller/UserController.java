package com.arturFerreira.cinema.controller;

import com.arturFerreira.cinema.controller.dto.CreateUserDto;
import com.arturFerreira.cinema.controller.dto.CreateUserResponseDto;
import com.arturFerreira.cinema.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserDto dto){
        var user = userService.createUser(dto);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();


        return ResponseEntity.created(uri).body(CreateUserResponseDto.fromEntity(user));
    }

}

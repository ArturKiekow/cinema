package com.arturFerreira.cinema.controller.dto.movieDtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record GenresRequestDto(
        @NotEmpty
        Set<String> genreNames
) {
}

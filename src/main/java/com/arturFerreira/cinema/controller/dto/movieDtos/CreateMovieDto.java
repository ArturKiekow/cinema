package com.arturFerreira.cinema.controller.dto.movieDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateMovieDto(
        @NotBlank
        String name,
        String description,
        @NotNull
        @Min(0)
        Integer durationInMinutes,
        Set<String> genres){


}
package com.arturFerreira.cinema.controller.dto.movieDtos;

import com.arturFerreira.cinema.entity.Movie;

import java.util.UUID;

public record GetAllMoviesDto(
        UUID id,
        String name,
        String description,
        Integer durationInMinutes
) {
    public static GetAllMoviesDto fromClass(Movie movie){
        return new GetAllMoviesDto(movie.getId(), movie.getName(), movie.getDescription(), movie.getDurationInMinutes());
    }
}

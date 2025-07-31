package com.arturFerreira.cinema.controller.dto.movieDtos;

import com.arturFerreira.cinema.controller.dto.genreDtos.GenreResponseDto;
import com.arturFerreira.cinema.entity.Movie;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record GetMovieResponseDto(
        UUID id,
        String name,
        String description,
        Integer durationInMinutes,
        Set<GenreResponseDto> genres,
        Set<SessionInMovieResponseDto> sessions
) {
    public static GetMovieResponseDto fromClass(Movie movie) {
        var genres = movie.getGenres()
                .stream()
                .map(GenreResponseDto::fromClass)
                .collect(Collectors.toSet());

        var sessions = movie.getSessions()
                .stream()
                .map(SessionInMovieResponseDto::fromClass)
                .collect(Collectors.toSet());

        return new GetMovieResponseDto(
                movie.getId(), 
                movie.getName(), 
                movie.getDescription(), 
                movie.getDurationInMinutes(),
                genres,
                sessions);
    }
}

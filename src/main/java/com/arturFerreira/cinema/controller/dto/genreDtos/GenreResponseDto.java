package com.arturFerreira.cinema.controller.dto.genreDtos;


import com.arturFerreira.cinema.entity.Genre;

public record GenreResponseDto(
        Long id,
        String name
) {
    public static GenreResponseDto fromClass(Genre genre){
        return new GenreResponseDto(genre.getId(), genre.getGenreName());
    }
}

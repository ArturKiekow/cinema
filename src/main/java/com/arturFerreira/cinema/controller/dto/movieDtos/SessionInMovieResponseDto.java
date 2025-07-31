package com.arturFerreira.cinema.controller.dto.movieDtos;

import com.arturFerreira.cinema.entity.CinemaRoom;
import com.arturFerreira.cinema.entity.Session;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionInMovieResponseDto(
        UUID id,
        CinemaRoom room,
        LocalDateTime dateTime
) {
    public static SessionInMovieResponseDto fromClass(Session session){
        return new SessionInMovieResponseDto(session.getId(), session.getRoom(), session.getDateTime());
    }
}

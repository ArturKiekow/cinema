package com.arturFerreira.cinema.controller;

import com.arturFerreira.cinema.controller.dto.movieDtos.CreateMovieDto;
import com.arturFerreira.cinema.controller.dto.movieDtos.CreateMovieResponseDto;
import com.arturFerreira.cinema.controller.dto.movieDtos.GetAllMoviesDto;
import com.arturFerreira.cinema.controller.dto.movieDtos.GetMovieResponseDto;
import com.arturFerreira.cinema.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllMoviesDto>> getAllMovies() {
        var movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies.stream().map(GetAllMoviesDto::fromClass).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMovieResponseDto> getMovie(@PathVariable UUID id){
        var movie = movieService.getMovie(id);
        return ResponseEntity.ok(GetMovieResponseDto.fromClass(movie));
    }

    @PostMapping
    public ResponseEntity<CreateMovieResponseDto> createMovie(@RequestBody @Valid CreateMovieDto dto) {
        var movie = movieService.createMovie(dto);
        return ResponseEntity.ok(CreateMovieResponseDto.fromClass(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}

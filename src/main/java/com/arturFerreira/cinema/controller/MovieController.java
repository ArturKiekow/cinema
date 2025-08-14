package com.arturFerreira.cinema.controller;

import com.arturFerreira.cinema.controller.dto.movieDtos.*;
import com.arturFerreira.cinema.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        var movies = movieService.getAllMovies()
                .stream()
                .map(GetAllMoviesDto::fromClass)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable UUID id){
        var movie = movieService.getMovie(id);
        return ResponseEntity.ok(MovieResponseDto.fromClass(movie));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody @Valid MovieDto dto) {
        var movie = movieService.createMovie(dto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movie.getId())
                .toUri();
        return ResponseEntity.created(uri).body(MovieResponseDto.fromClass(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateById(@PathVariable UUID id, @RequestBody @Valid MovieDto dto){
        var movie = movieService.updateMovie(id, dto);
        return ResponseEntity.ok(MovieResponseDto.fromClass(movie));
    }

    @PatchMapping("/{id}/genres")
    public ResponseEntity<MovieResponseDto> addGenres(@PathVariable UUID id, @RequestBody GenresRequestDto genreNames) {
        var movie = movieService.addGenres(id, genreNames);
        return ResponseEntity.ok(MovieResponseDto.fromClass(movie));
    }

    @DeleteMapping("/{id}/genres")
    public ResponseEntity<MovieResponseDto> removeGenres(@PathVariable UUID id, @RequestBody GenresRequestDto genreNames) {
        var movie = movieService.removeGenres(id, genreNames);
        return ResponseEntity.ok(MovieResponseDto.fromClass(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.arturFerreira.cinema.services;

import com.arturFerreira.cinema.controller.dto.movieDtos.CreateMovieDto;
import com.arturFerreira.cinema.exceptions.MovieNotFoundException;
import com.arturFerreira.cinema.entity.Movie;
import com.arturFerreira.cinema.repository.GenreRepository;
import com.arturFerreira.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public Movie getMovie(UUID id) {
        var movie = movieRepository.findById(id);
        if(movie.isPresent()){
            return movie.get();
        }
        throw new MovieNotFoundException("Não existe filme com o id:" + id);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }


    public Movie createMovie(CreateMovieDto dto) {
        var genres = dto.genres()
                .stream()
                .map(genreRepository::findByGenreName)
                .collect(Collectors.toSet());

        var movie = new Movie();
        movie.setName(dto.name());
        movie.setDurationInMinutes(dto.durationInMinutes());
        movie.setDescription(dto.description());
        movie.setGenres(genres);

        return movieRepository.save(movie);
    }

    public void deleteById(UUID id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Não existe filme com o id:" + id);
        }
        movieRepository.deleteById(id);
    }
}

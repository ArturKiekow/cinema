package com.arturFerreira.cinema.services;

import com.arturFerreira.cinema.controller.dto.movieDtos.GenresRequestDto;
import com.arturFerreira.cinema.controller.dto.movieDtos.MovieDto;
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
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Não existe filme com o id:" + id));
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }


    public Movie createMovie(MovieDto dto) {
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

    public Movie updateMovie(UUID id, MovieDto dto) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Não existe filme com o id:" + id));

        var genres = dto.genres()
                .stream()
                .map(genreRepository::findByGenreName)
                .collect(Collectors.toSet());

        movie.setName(dto.name());
        movie.setDurationInMinutes(dto.durationInMinutes());
        movie.setDescription(dto.description());
        movie.setGenres(genres);
        System.out.print(movie);

        return movieRepository.save(movie);
    }

    public Movie addGenres(UUID id, GenresRequestDto genres) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Não existe filme com o id:" + id));

        var genresBd = genres.genreNames()
                .stream()
                .map(genreRepository::findByGenreName)
                .collect(Collectors.toSet());
        movie.addGenre(genresBd);

        return movieRepository.save(movie);
    }

    public Movie removeGenres(UUID id, GenresRequestDto genres) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Não existe filme com o id:" + id));

        var genresBd = genres.genreNames()
                .stream()
                .map(genreRepository::findByGenreName)
                .collect(Collectors.toSet());

        movie.removeGenre(genresBd);

        return movieRepository.save(movie);
    }


    public void deleteById(UUID id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Não existe filme com o id:" + id);
        }
        movieRepository.deleteById(id);
    }


}

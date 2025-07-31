package com.arturFerreira.cinema.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    public Genre() {
    }

    public Long getId() {
        return Id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genre) {
        this.genreName = genre;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public enum Values {
        ACAO(1L, "ação"),
        COMEDIA(2L, "comédia"),
        DRAMA(3L, "drama"),
        FICCAO(4L, "ficção"),
        CIENTIFICO(5L, "científico"),
        TERROR(6L, "terror"),
        FANTASIA(7L, "fantasia"),
        SUSPENCE(8L, "suspence"),
        ROMANCE(9L, "romance"),
        MUSICAL(10L, "musical");

        final Long genreId;
        final String genreName;

        Values(Long genreId, String genreName) {
            this.genreId = genreId;
            this.genreName = genreName;
        }

        public Long getGenreId() {
            return genreId;
        }

        public String getGenreName() {
            return genreName;
        }
    }


}

package com.arturFerreira.cinema.repository;

import com.arturFerreira.cinema.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    public Genre findByGenreName(String name);
}

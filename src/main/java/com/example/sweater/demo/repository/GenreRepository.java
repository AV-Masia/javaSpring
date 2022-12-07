package com.example.sweater.demo.repository;

import com.example.sweater.demo.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre getGenreByName(String name);

}

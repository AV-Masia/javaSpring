package com.example.sweater.demo.repository;

import com.example.sweater.demo.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre getGenreByName(String name);

    @Query(value = "SELECT * FROM genres WHERE id=?1", nativeQuery = true)
    Genre getById(Long id);

}

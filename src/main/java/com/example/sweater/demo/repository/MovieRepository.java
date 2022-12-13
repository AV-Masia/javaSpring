package com.example.sweater.demo.repository;

import com.example.sweater.demo.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movies WHERE id=?1", nativeQuery = true)
    Movie getById(Long id);

    Movie save(Movie movie);

}

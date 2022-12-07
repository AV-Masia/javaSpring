package com.example.sweater.demo.service;

import com.example.sweater.demo.model.Movie;
import com.example.sweater.demo.model.form.MovieForm;

import java.util.List;

public interface MovieService {

    boolean createMovie(MovieForm movieForm);

    Movie updateMovie(MovieForm movieForm);

    List<Movie> getAllMovies();

    boolean deleteMovieById(Long id);

    Movie getMovieById(Long id);

    boolean uploadMoviesFromExternalApi(String limit);

    List<Movie> filterMoviesByGenre(String string);
}

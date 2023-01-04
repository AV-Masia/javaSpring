package fs.spring.vue.service;

import fs.spring.vue.model.Movie;
import fs.spring.vue.model.form.MovieForm;

import java.util.List;

public interface MovieService {

    Movie createMovie(MovieForm movieForm);

    Movie updateMovie(MovieForm movieForm);

    List<Movie> getAllMovies();

    boolean deleteMovieById(Long id);

    Movie getMovieById(Long id);

    boolean uploadMoviesFromExternalApi(String limit);

    List<Movie> filterMoviesByGenre(String string);
}

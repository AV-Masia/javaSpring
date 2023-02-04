package fs.spring.vue.controller;

import fs.spring.vue.model.Movie;
import fs.spring.vue.model.form.MovieForm;
import fs.spring.vue.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {

    @Autowired
    public MovieService movieService;

    @RequestMapping(value = "/api/create_movie", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieForm movieForm) {
        Movie movie = movieService.createMovie(movieForm);
        return movie != null
                ? ResponseEntity.ok().body(movie)
                : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/api/admin/delete_movie")
    public ResponseEntity<String> deleteMovie(@RequestParam(value = "id", required = true) Long id) {
        return movieService.deleteMovieById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/api/update_movie", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Movie> updateMovie(@RequestBody MovieForm movieForm) {
        Movie movie = movieService.updateMovie(movieForm);
        return movie != null
                ? ResponseEntity.ok().body(movie)
                : ResponseEntity.internalServerError().build();
    }

    @GetMapping(value = "/api/get_movie")
    public ResponseEntity<Movie> getMovieById(@RequestParam(value = "id", required = true)Long id) {
        Movie movie = movieService.getMovieById(id);
        return movie != null
                ? ResponseEntity.ok().body(movie)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/api/get_all_movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return movies != null
                ? ResponseEntity.ok().body(movies)
                : ResponseEntity.internalServerError().build();
    }

    @GetMapping(value = "/api/filter_movies_by_genre")
    public ResponseEntity<List<Movie>> filterMoviesByGenre(@RequestParam(value = "genre", required = true) String string) {
        List<Movie> movies = movieService.filterMoviesByGenre(string);
        return movies != null
                ? ResponseEntity.ok().body(movies)
                : ResponseEntity.internalServerError().build();
    }
}
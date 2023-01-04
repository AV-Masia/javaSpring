package fs.spring.vue.controller;

import fs.spring.vue.model.Genre;
import fs.spring.vue.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;


    @GetMapping("/api/get_all_genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return genres != null
                ? ResponseEntity.ok().body(genres)
                : ResponseEntity.internalServerError().build();
    }

    @GetMapping(value = "/api/get_genre")
    public ResponseEntity<Genre> getGenreId(@RequestParam(value = "id", required = true)Long id) {
        Genre genre = genreService.getGenreById(id);
        return genre != null
                ? ResponseEntity.ok().body(genre)
                : ResponseEntity.internalServerError().build();
    }

    @RequestMapping(value = "/api/admin/create_genre", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre genreEntity = genreService.create(genre);
        return genreEntity != null
                ? ResponseEntity.ok().body(genreEntity)
                : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/api/admin/delete_genre")
    public ResponseEntity<String> deleteGenre(@RequestParam(value = "id", required = true) Long id) {
        return genreService.deleteGenreById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/api/get_genre_by_name")
    public ResponseEntity<Genre> getGenreName(@RequestParam(value = "name", required = true)String name) {
        Genre genre = genreService.getGenreByName(name);
        return genre != null
                ? ResponseEntity.ok().body(genre)
                : ResponseEntity.internalServerError().build();
    }
}

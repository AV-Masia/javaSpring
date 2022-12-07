package com.example.sweater.demo.controller;

import com.example.sweater.demo.model.Genre;
import com.example.sweater.demo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/api/admin/create_genre")
    public ResponseEntity<String> createGenre(Genre genre) {
        return genreService.create(genre)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/api/admin/delete_genre/{id}")
    public ResponseEntity<String> deleteGenre(Long id) {
        return genreService.deleteGenreById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/get_all_genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return genres != null
                ? ResponseEntity.ok().body(genres)
                : ResponseEntity.internalServerError().build();
    }

    @GetMapping("/api/get_genre/{id}")
    public ResponseEntity<Genre> getGenreById(Long id) {
        Genre genre = genreService.getGenreById(id);
        return genre != null
                ? ResponseEntity.ok().body(genre)
                : ResponseEntity.internalServerError().build();
    }
}

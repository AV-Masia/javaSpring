package com.example.sweater.demo.controller;

import com.example.sweater.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/api/admin/upload_movies")
    public ResponseEntity<String> uploadMovies(@RequestParam("limit") String limit) {
        return movieService.uploadMoviesFromExternalApi(limit)
                ? ResponseEntity.ok().build()
                : ResponseEntity.internalServerError().build();
    }
}

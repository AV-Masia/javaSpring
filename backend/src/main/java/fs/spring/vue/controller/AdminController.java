package fs.spring.vue.controller;

import fs.spring.vue.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/api/admin/upload_movies")
    public ResponseEntity<String> uploadMovies(@RequestParam("limit") String limit) {
        try{
            Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Limit is empty or bad parameter");
        }
        return movieService.uploadMoviesFromExternalApi(limit)
                ? ResponseEntity.ok().build()
                : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping(value = "/api/admin/delete_all_movies")
    public ResponseEntity<String> cleanMovies() {
        return movieService.cleanMovies()
                ? ResponseEntity.ok().build()
                : ResponseEntity.internalServerError().build();
    }
}

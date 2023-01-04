package fs.spring.vue.service;

import fs.spring.vue.model.Genre;

import java.util.List;

public interface GenreService {

    Genre create(Genre genre);

    boolean deleteGenreById(Long id);

    List<Genre> getAllGenres();

    Genre getGenreById(Long id);

    Genre getGenreByName(String name);

    Genre buildGenre(String str);

}

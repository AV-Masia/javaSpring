package fs.spring.vue.service.impl;

import fs.spring.vue.model.Genre;
import fs.spring.vue.repository.GenreRepository;
import fs.spring.vue.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre create(Genre genre) {
        Genre temp = Genre.builder().build();
            try{
               temp = genreRepository.save(genre);
            } catch (Exception e) {
                return temp;
            }
        return temp;
    }

    @Override
    public boolean deleteGenreById(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Genre> getAllGenres() {
        return StreamSupport
                .stream(genreRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.getById(id);
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreRepository.getGenreByName(name);
    }

    @Override
    public Genre buildGenre(String name) {
        Genre genre = genreRepository.getGenreByName(name);
        if(genre != null) {
            return genre;
        }
        genre = Genre.builder().name(name).build();
        return genreRepository.save(genre);
    }
}

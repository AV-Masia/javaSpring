package com.example.sweater.demo.service.impl;

import com.example.sweater.demo.model.Genre;
import com.example.sweater.demo.repository.GenreRepository;
import com.example.sweater.demo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public boolean create(Genre genre) {
        try {
            genreRepository.save(genre);
        } catch (Exception e) {
            return false;
        }
        return true;
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
        return Stream.of(genreRepository.findAll().iterator()).map(e -> getAllGenres()).findAny().get();
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).get();
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
        genreRepository.save(genre);
        return genre;
    }

//    @Override
//    @InjectService(required = true)
////    @Bean(value = "genreService")
//    public GenreService getInstance(){
//        return this;
//    }

//    public GenreDeserializer getGenreDeserializerInstance(){
//        return new GenreDeserializer();
//    }

//    public class GenreDeserializer extends StdDeserializer<Genre> {
//
////    @Autowired
////    private GenreService genreService;
//
//        @Autowired
//        private UserService userService;
//
//        public GenreDeserializer() {
//            this(null);
////        genreService = new GenreServiceImpl();
////        genreService = genreService.getInstance();
//        }
//
//        public GenreDeserializer(Class<?> vc) {
//            super(vc);
//        }
//
//        @Override
//        public Genre deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//            JsonNode node = jp.getCodec().readTree(jp);
////        return new GenreServiceImpl().getInstance().buildGenre(node.textValue());
//
////                genreService.buildGenre(node.textValue());
//            userService.getUserById(11L);
//
//            return new Genre();
//        }
//    }
}

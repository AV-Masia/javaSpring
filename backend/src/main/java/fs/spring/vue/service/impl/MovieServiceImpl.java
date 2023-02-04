package fs.spring.vue.service.impl;

import fs.spring.vue.model.Genre;
import fs.spring.vue.model.Movie;
import fs.spring.vue.model.form.MovieForm;
import fs.spring.vue.repository.MovieRepository;
import fs.spring.vue.service.GenreDeserializer;
import fs.spring.vue.service.GenreService;
import fs.spring.vue.service.MovieService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreDeserializer genreDeserializer;

    @Override
    public Movie createMovie(MovieForm movieForm) {
           return  movieRepository.save(buildMovieFromExternalForm(movieForm));
    }

    @Override
    public Movie updateMovie(MovieForm movieForm) {
        return movieRepository.save(buildMovieFromExternalForm(movieForm));
    }

    @Override
    public List<Movie> getAllMovies() {
        return StreamSupport
                .stream(movieRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteMovieById(Long id) {
        try {
            movieRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.getById(id);
    }

    @Modifying
    @Override
    public boolean uploadMoviesFromExternalApi(String limit) {
        String e_url = "http://localhost:4000/movies"; //external API with above JSON data

        e_url = limit != null
                ? e_url + "?limit=" + limit
                : e_url;

        RestTemplate rt = new RestTemplate();
        boolean result = true;
        try {
            ResponseEntity<Object> responseEntity = rt.getForEntity(e_url, Object.class);
            Map<String, Object> map1 = Collections.unmodifiableMap((Map<String, Object>) responseEntity.getBody());
            List<Map<String, Object>> listDataOfMovies = (List<Map<String, Object>>) map1.get("data");
            List<Movie> listMovies = buildListMovies(listDataOfMovies);
            movieRepository.saveAll(listMovies);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Movie> filterMoviesByGenre(String name) {
        Genre genre = genreService.getGenreByName(name);
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()).stream()
                .filter(e -> e.getGenres().contains(genre))
                .collect(Collectors.toList());
    }

    @Override
    public boolean cleanMovies() {
        try {
            movieRepository.deleteAll();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private List<Movie> buildListMovies(List<Map<String, Object>> mapList) {
        return mapList.stream()
                .map(this::createMovieFromData)
                .collect(Collectors.toList());
    }

    private Movie createMovieFromData(Map<String, Object> dataElement) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Genre.class, genreDeserializer.getInstance());
        mapper.registerModule(module);
        mapper.addMixIn(Movie.class, Movie.MixIn.class);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(dataElement, Movie.class);
    }
    private Movie buildMovieFromExternalForm(MovieForm movieForm) {
        Set<Genre> genres = movieForm.getGenres()
                .stream()
                .map(e -> genreService.buildGenre(e.getName()))
                .collect(Collectors.toSet());
        Movie movie = Movie.builder()
                .title(movieForm.getTitle())
                .tagline(movieForm.getTagline())
                .vote_average(movieForm.getVote_average())
                .vote_count(movieForm.getVote_count())
                .release_date(movieForm.getRelease_date())
                .poster_path(movieForm.getPoster_path())
                .overview(movieForm.getOverview())
                .budget(movieForm.getBudget())
                .revenue(movieForm.getRevenue())
                .genres(genres)
                .runtime(movieForm.getRuntime())
                .build();
        if(movieForm.getId() != null) {
            movie.setId(movieForm.getId());
            return movie;
        }
        return movie;
    }

}

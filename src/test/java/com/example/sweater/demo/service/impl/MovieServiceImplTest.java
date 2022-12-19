package com.example.sweater.demo.service.impl;

import com.example.sweater.demo.model.Genre;
import com.example.sweater.demo.model.Movie;
import com.example.sweater.demo.model.form.MovieForm;
import com.example.sweater.demo.repository.MovieRepository;
import com.example.sweater.demo.service.GenreDeserializer;
import com.example.sweater.demo.service.GenreService;
import com.example.sweater.demo.service.MovieService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(JUnitParamsRunner.class)
@SpringBootTest
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreService genreService;

    @Mock
    private GenreDeserializer genreDeserializer;

    @InjectMocks
    private MovieService movieService = new MovieServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    private static class TestDataStorage {
        Genre genre1 = Genre.builder().id(1L).name("Horrors").build();
        Genre genre2 = Genre.builder().id(2L).name("Drama").build();
        Set<Genre> genres = Set.of(genre1, genre2);

        Movie movie1 = Movie.builder().id(1L)
                .title("Test title").tagline("Don't miss")
                .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
                .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
                .overview("Believing they have left")
                .budget(55000000).genres(genres).build();
        Movie movie2 = Movie.builder().id(2L).title("Something new")
                .tagline("Do it").vote_average(8.5)
                .vote_count(1195).runtime(90).revenue(136906000).release_date("2020-01-01")
                .poster_path("https://image.tmdb.org/t/p/w500/3.jpg").overview("Believing they have left")
                .budget(22000000).genres(genres).build();
        Movie movie3 = Movie.builder().id(1L)
                .title("New").tagline("Don't miss")
                .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
                .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
                .overview("Believing they have left")
                .budget(55000000).genres(genres).build();

        Movie movie4 = Movie.builder().id(2L).title("Something new")
                .tagline("New1").vote_average(8.5)
                .vote_count(1195).runtime(90).revenue(136906000).release_date("2020-01-01")
                .poster_path("https://image.tmdb.org/t/p/w500/3.jpg").overview("Believing they have left")
                .budget(22000000).genres(genres).build();

        List<Movie> movies1 = Lists.newArrayList(movie1, movie2);
        List<Movie> movies2 = List.of(movie1);

        MovieForm movieForm1 = MovieForm.builder()
                .title("Test title").tagline("Don't miss")
                .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
                .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
                .overview("Believing they have left")
                .budget(55000000).genres(genres).build();
        MovieForm movieForm2 = MovieForm.builder()
                .title("Something new").tagline("Do it")
                .vote_average(8.5).vote_count(1195).runtime(90).revenue(136906000)
                .release_date("2020-01-01").poster_path("https://image.tmdb.org/t/p/w500/3.jpg")
                .overview("Believing they have left")
                .budget(22000000).genres(genres).build();
        MovieForm movieForm3 = MovieForm.builder().id(1L)
                .title("New").tagline("Don't miss")
                .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
                .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
                .overview("Believing they have left")
                .budget(55000000).genres(genres).build();
        MovieForm movieForm4 = MovieForm.builder().id(2L).title("Something new")
                .tagline("New1").vote_average(8.5)
                .vote_count(1195).runtime(90).revenue(136906000).release_date("2020-01-01")
                .poster_path("https://image.tmdb.org/t/p/w500/3.jpg").overview("Believing they have left")
                .budget(22000000).genres(genres).build();
    }

    private static final TestDataStorage testDataStorage = new TestDataStorage();

    @Parameterized.Parameters
    private Object[] paramsMovies() {
        return new Object[][]{
                {testDataStorage.movies1, testDataStorage.movies1},
                {testDataStorage.movies2, testDataStorage.movies2},
        };
    }

    @Parameterized.Parameters
    private Object[] paramsCreateMoviesFromMoviesForm() {
        return new Object[][]{
                {testDataStorage.movieForm1, testDataStorage.movie1},
                {testDataStorage.movieForm2, testDataStorage.movie2},
        };
    }
    @Parameterized.Parameters
    private Object[] paramsUpdateMoviesFromMoviesForm() {
        return new Object[][]{
                {testDataStorage.movieForm3, testDataStorage.movie3},
                {testDataStorage.movieForm4, testDataStorage.movie4},
        };
    }

    @Parameterized.Parameters
    private Object[] paramsNameGenre() {
        return new Object[][]{
                {"Horrors",testDataStorage.genre1, testDataStorage.movies1}
        };
    }
    @Test
    @Parameters(method = "paramsMovies")
    public void verifyGetAllMovies(List<Movie> movies, List<Movie> expectedMovies) {
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> actual = movieService.getAllMovies();

        verify(movieRepository, times(1)).findAll();
        assertEquals(actual, expectedMovies);
    }


    @Test
    public void verifyGetMovieById() {
        when(movieRepository.getById(1L)).thenReturn(testDataStorage.movie1);
        Movie actual = movieService.getMovieById(1L);
        verify(movieRepository, times(1)).getById(1L);
        assertEquals(actual, testDataStorage.movie1);
    }

    @Test
    public void verifyDeleteMovieById() {
        doNothing().when(movieRepository).deleteById(1L);
        movieService.deleteMovieById(1L);
        verify(movieRepository, times(1)).deleteById(1L);
    }

    @Test
    @Parameters(method = "paramsCreateMoviesFromMoviesForm")
    public void verifyCreateMovie(MovieForm movieForm, Movie expectedMovie) {
        movieForm.getGenres().forEach(e->
        {
            when(genreService.buildGenre(e.getName())).thenReturn(Genre.builder().id(e.getId()).name(e.getName()).build());
        });
        when(movieRepository.save(expectedMovie)).thenReturn(expectedMovie);

        Movie actual = movieService.createMovie(movieForm);

        verify(movieRepository, times(1)).save(expectedMovie);
        assertEquals(actual, expectedMovie);
    }

    @Test
    @Parameters(method = "paramsUpdateMoviesFromMoviesForm")
    public void verifyUpdateMovie(MovieForm movieForm, Movie expectedMovie) {
        movieForm.getGenres().forEach(e->
        {
            when(genreService.buildGenre(e.getName())).thenReturn(Genre.builder().id(e.getId()).name(e.getName()).build());
        });
        when(movieRepository.save(expectedMovie)).thenReturn(expectedMovie);

        Movie actual = movieService.updateMovie(movieForm);

        verify(movieRepository, times(1)).save(expectedMovie);
        assertEquals(actual, expectedMovie);
    }

    @Test
    @Parameters(method = "paramsNameGenre")
    public void verifyFilterMoviesByName(String name, Genre genre, List<Movie> expectedMovies){
        when(genreService.getGenreByName(name)).thenReturn(genre);
        when(movieRepository.findAll()).thenReturn(expectedMovies);

        List<Movie> actual = movieService.filterMoviesByGenre(name);
        verify(movieRepository, times(1)).findAll();
        assertEquals(actual, expectedMovies);
    }

}
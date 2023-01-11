//package fs.spring.vue.controller;
//
//import fs.spring.vue.model.Genre;
//import fs.spring.vue.model.Movie;
//import fs.spring.vue.model.form.MovieForm;
//import fs.spring.vue.service.MovieService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//import org.assertj.core.util.Lists;
//import org.junit.Before;
//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.rules.SpringClassRule;
//import org.springframework.test.context.junit4.rules.SpringMethodRule;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//import java.util.Set;
//
//import static org.hamcrest.Matchers.containsInAnyOrder;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(JUnitParamsRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@AutoConfigureMockMvc
//public class MoviesControllerTest {
//
//    @ClassRule
//    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
//    @Rule
//    public final SpringMethodRule springMethodRule = new SpringMethodRule();
//
//    @MockBean
//    private MovieService movieService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
//                .dispatchOptions(true).build();
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private static class TestDataStorage {
//        Genre genre1 = Genre.builder().id(1L).name("Horrors").build();
//        Genre genre2 = Genre.builder().id(2L).name("Drama").build();
//        Genre genre1withOutId = Genre.builder().name("Horrors").build();
//        Genre genre2withOutId= Genre.builder().name("Drama").build();
//        Set<Genre> genres = Set.of(genre1, genre2);
//        Set<Genre> genresWithOutId = Set.of(genre1withOutId, genre2withOutId);
//
//        Movie movieUp1 = Movie.builder().id(1L)
//            .title("Test title").tagline("Don't miss")
//            .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
//            .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
//            .overview("Believing they have left")
//            .budget(55000000).genres(genres).build();
//        Movie movieUp2 = Movie.builder().id(2L).title("Something new")
//            .tagline("Do it").vote_average(8.5)
//            .vote_count(1195).runtime(90).revenue(136906000).release_date("2020-01-01")
//            .poster_path("https://image.tmdb.org/t/p/w500/3.jpg").overview("Believing they have left")
//            .budget(22000000).genres(genres).build();
//
//        Movie movie1 = Movie.builder().id(1L)
//                .title("Message").tagline("Don't forgot ")
//                .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
//                .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
//                .overview("Believing they have left")
//                .budget(55000000).genres(genres).build();
//        Movie movie2 = Movie.builder().id(2L).title("Game over")
//                .tagline("Do it").vote_average(8.5)
//                .vote_count(1195).runtime(90).revenue(136906000).release_date("2020-01-01")
//                .poster_path("https://image.tmdb.org/t/p/w500/3.jpg").overview("Believing they have left")
//                .budget(22000000).genres(genres).build();
//
//        List<Movie> movies1 = Lists.newArrayList(movie1, movie2);
//        List<Movie> movies2 = List.of(movie1);
//
//        MovieForm movieForm1 = MovieForm.builder().id(1L)
//            .title("Test title").tagline("Don't miss")
//            .vote_average(8.5).vote_count(1195).runtime(106).revenue(136906000)
//            .release_date("2018-02-07").poster_path("https://image.tmdb.org/t/p.jpg")
//            .overview("Believing they have left")
//            .budget(55000000).genres(genresWithOutId).build();
//        MovieForm movieForm2 = MovieForm.builder().id(2L)
//            .title("Something new").tagline("Do it")
//            .vote_average(8.5).vote_count(1195).runtime(90).revenue(136906000)
//            .release_date("2020-01-01").poster_path("https://image.tmdb.org/t/p/w500/3.jpg")
//            .overview("Believing they have left")
//            .budget(22000000).genres(genresWithOutId).build();
//    }
//
//    public static final TestDataStorage testDataStorage = new TestDataStorage();
//
//    @Parameterized.Parameters
//    public static Object[] paramsMovies() {
//        return new Object[][]{
//            {1L, testDataStorage.movie1},
//            {2L, testDataStorage.movie2}
//        };
//    }
//
//    @Parameterized.Parameters
//    public static Object[] paramsListMovies() {
//        return new Object[][]{
//                {testDataStorage.movies1},
//                {testDataStorage.movies2}
//        };
//    }
//
//    @Parameterized.Parameters
//    public static Object[] paramsMovieForm() {
//        return new Object[][]{
//                {testDataStorage.movieForm1, testDataStorage.movieUp1},
//                {testDataStorage.movieForm2, testDataStorage.movieUp2}
//        };
//    }
//
//    @Parameterized.Parameters
//    public static Object[] paramsFilterMovies() {
//        return new Object[][]{
//                {"Horrors", testDataStorage.movies1},
////                {"Drama", testDataStorage.movies2}
//        };
//    }
//
//    @Test
//    @Parameters(method = "paramsMovies")
//    public void verifyGetMovieById_status200(long id, Movie movie) throws Exception {
//        when(movieService.getMovieById(id)).thenReturn(movie);
//        mockMvc.perform(
//            get("/api/get_movie")
//                .param("id", String.valueOf(id)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.title").value(movie.getTitle()))
//                .andExpect(jsonPath("$.tagline").value(movie.getTagline()))
//                .andExpect(jsonPath("$.vote_average").value(movie.getVote_average()))
//                .andExpect(jsonPath("$.vote_count").value(movie.getVote_count()))
//                .andExpect(jsonPath("$.release_date").value(movie.getRelease_date()))
//                .andExpect(jsonPath("$.poster_path").value(movie.getPoster_path()))
//                .andExpect(jsonPath("$.budget").value(movie.getBudget()))
//                .andExpect(jsonPath("$.revenue").value(movie.getRevenue()))
//                .andExpect(jsonPath("$.runtime").value(movie.getRuntime()))
//                .andExpect(jsonPath("$.overview").value(movie.getOverview()))
//                .andExpect(jsonPath("$.genres[*].name", containsInAnyOrder(movie.getGenres().stream().map(Genre::getName).toArray())));
//
//        verify(movieService, times(1)).getMovieById(id);
//    }
//
//    @Test
//    public void verifyGetMovieById_status204() throws Exception {
//        when(movieService.getMovieById(5L)).thenReturn(null);
//        mockMvc.perform(
//            get("/api/get_movie").param("id", String.valueOf(5L)))
//            .andDo(print())
//            .andExpect(status().isNoContent());
//
//        verify(movieService, times(1)).getMovieById(5L);
//    }
//
//    @Test
//    @Parameters(method = "paramsListMovies")
//    public void verifyGetAllMovies_status200(List<Movie> movies) throws Exception {
//        when(movieService.getAllMovies()).thenReturn(movies);
//        mockMvc.perform(get("/api/get_all_movies"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(movies)));
//
//        verify(movieService, times(1)).getAllMovies();
//    }
//
//    @Test
//    public void verifyDeleteMovieById_status200() throws Exception {
//        long id = 5L;
//        when(movieService.deleteMovieById(id)).thenReturn(true);
//
//        mockMvc.perform(
//                        delete("/api/admin/delete_movie").param("id", String.valueOf(id)))
//                .andExpect(status().isOk());
//
//        verify(movieService, times(1)).deleteMovieById(id);
//    }
//
//    @Test
//    @Parameters(method = "paramsMovieForm")
//    public void verifyUpdateMovie_status200(MovieForm movieForm, Movie movie) throws Exception {
//        when(movieService.updateMovie(any(MovieForm.class))).thenReturn(movie);
//        mockMvc.perform(
//                        put("/api/update_movie")
//                                .content(objectMapper.writeValueAsString(movieForm))
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(movie.getId()))
//                .andExpect(jsonPath("$.title").value(movie.getTitle()))
//                .andExpect(jsonPath("$.tagline").value(movie.getTagline()))
//                .andExpect(jsonPath("$.vote_average").value(movie.getVote_average()))
//                .andExpect(jsonPath("$.vote_count").value(movie.getVote_count()))
//                .andExpect(jsonPath("$.release_date").value(movie.getRelease_date()))
//                .andExpect(jsonPath("$.poster_path").value(movie.getPoster_path()))
//                .andExpect(jsonPath("$.budget").value(movie.getBudget()))
//                .andExpect(jsonPath("$.revenue").value(movie.getRevenue()))
//                .andExpect(jsonPath("$.runtime").value(movie.getRuntime()))
//                .andExpect(jsonPath("$.overview").value(movie.getOverview()))
//                .andExpect(jsonPath("$.genres[*].name", containsInAnyOrder(movie.getGenres().stream().map(Genre::getName).toArray())))
//        ;
//
//        verify(movieService, times(1)).updateMovie(any(MovieForm.class));
//    }
//
//    @Test
//    @Parameters(method = "paramsFilterMovies")
//    public void verifyFilterMovies_status200(String name, List<Movie> movies) throws Exception {
//        when(movieService.filterMoviesByGenre(any())).thenReturn(movies);
//        mockMvc.perform(get("/api/filter_movies").param("genre", String.valueOf(name)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(movies)));
//
//        verify(movieService, times(1)).filterMoviesByGenre(name);
//    }
//
//}
package com.example.sweater.demo.controller;

import com.example.sweater.demo.model.Genre;
import com.example.sweater.demo.service.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class GenreControllerTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @MockBean
    public GenreService genreService;
//    @InjectMocks
//    public GenreController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
//                .dispatchOptions(true).build();
        MockitoAnnotations.openMocks(this);
    }

    private static class TestDataStorage {
        Genre genre1 = Genre.builder().id(1L).name("Horrors").build();
        Genre genre2 = Genre.builder().id(2L).name("Drama").build();
        Genre genre3 = Genre.builder().id(3L).name("Test").build();
        Genre genre1withOutId = Genre.builder().name("Horrors").build();
        Genre genre2withOutId = Genre.builder().name("Drama").build();
        List<Genre> genres = List.of(genre1, genre2);
        List<Genre> genres2 = List.of(genre1, genre2, genre3);
        Set<Genre> genresWithOutId = Set.of(genre1withOutId, genre2withOutId);
    }

    public static TestDataStorage testDataStorage = new TestDataStorage();

    @Parameterized.Parameters
    public static Object [] paramsListGenres(){
            return new Object[][]{
                    {testDataStorage.genres},
                    {testDataStorage.genres2}
            };
    }

    @Parameterized.Parameters
    public static Object [] paramsNameGenre(){
        return new Object[][]{
                {"Drama", testDataStorage.genre2},
                {"Test", testDataStorage.genre3},
                {"Horrors", testDataStorage.genre1},
        };
    }
    @Parameterized.Parameters
    public static Object [] paramsIdGenre(){
        return new Object[][]{
                {1L, testDataStorage.genre1},
                {2L, testDataStorage.genre2},
                {3L, testDataStorage.genre3},
        };
    }

    @Parameterized.Parameters
    public static Object [] paramsCreateGenre(){
        return new Object[][]{
                {testDataStorage.genre1withOutId, testDataStorage.genre1},
                {testDataStorage.genre2withOutId, testDataStorage.genre2}
        };
    }

    @Test
    @Parameters(method = "paramsListGenres")
    public void verifyGetAllGenres(List<Genre> genres) throws Exception {
        when(genreService.getAllGenres()).thenReturn(genres);
        mockMvc.perform(get("/api/get_all_genres"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(genres)));

        verify(genreService, times(1)).getAllGenres();

    }

    @Test
    @Parameters(method = "paramsIdGenre")
    public void verifyGetGenreId(Long id, Genre genre) throws Exception {
        when(genreService.getGenreById(id)).thenReturn(genre);
        mockMvc.perform(get("/api/get_genre")
                        .param("id",String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(genre)));

        verify(genreService, times(1)).getGenreById(id);
    }

    @Test
    @Parameters(method = "paramsCreateGenre")
    public void createGenre(Genre genre, Genre expectedGenre) throws Exception {
        when(genreService.create(genre)).thenReturn(expectedGenre);
        mockMvc.perform(put("/api/admin/create_genre")
                        .content(objectMapper.writeValueAsString(genre))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedGenre)));

        verify(genreService,times(1)).create(genre);
    }

    @Test
    public void deleteGenre() throws Exception{
        when(genreService.deleteGenreById(2L)).thenReturn(true);
        mockMvc.perform(delete("/api/admin/delete_genre")
                        .param("id",String.valueOf(2L)))
                .andExpect(status().isOk());

        verify(genreService, times(1)).deleteGenreById(2L);
    }

    @Test
    @Parameters(method = "paramsNameGenre")
    public void getGenreName(String name, Genre genre) throws Exception{
        when(genreService.getGenreByName(any())).thenReturn(genre);
        mockMvc.perform(get("/api/get_genre_by_name").param("name", String.valueOf(name)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(genre)));

        verify(genreService, times(1)).getGenreByName(name);
    }
}
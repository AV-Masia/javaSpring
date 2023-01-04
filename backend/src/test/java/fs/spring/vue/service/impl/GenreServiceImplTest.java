package fs.spring.vue.service.impl;

import fs.spring.vue.model.Genre;
import fs.spring.vue.repository.GenreRepository;
import fs.spring.vue.service.GenreService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest
public class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService = new GenreServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static class TestDataStorage{
        Genre emptyGenre = Genre.builder().build();
        Genre genre1 = Genre.builder().id(1L).name("Horrors").build();
        Genre genre2 = Genre.builder().id(2L).name("Drama").build();
        List<Genre> expectedGenres1 = Lists.newArrayList(genre1, genre2);
        List<Genre> expectedGenres2 = List.of(genre1);
    }

    private static final TestDataStorage testDataStorage = new TestDataStorage();

    @SuppressWarnings("unused")
    @Parameterized.Parameters
    private Object[] paramsForCreateGenre() {
        return new Object[][]{
                {testDataStorage.genre1, testDataStorage.genre1, true},
                {testDataStorage.genre2, testDataStorage.genre2, true},
                {null, testDataStorage.emptyGenre, false}
        };
    }

    @SuppressWarnings("unused")
    @Parameterized.Parameters
    private Object[] paramsForGetAllGenres() {
        return new Object[][]{
                {testDataStorage.expectedGenres1, testDataStorage.expectedGenres1},
                {testDataStorage.expectedGenres2, testDataStorage.expectedGenres2}
        };
    }

    @SuppressWarnings("unused")
    @Parameterized.Parameters
    private Object[] paramsNameGenreAndEntityGenre() {
        return new Object[][]{
                {"Horrors", testDataStorage.genre1},
                {"Drama", testDataStorage.genre2}
        };
    }

    @Test
    @Parameters(method = "paramsForCreateGenre")
    public void verifyCreateGenre(Genre genre, Genre expected, boolean trigger) {
        if(trigger) {
//            when(genreRepository.save(genre)).thenReturn(expected);
            doReturn(expected).when(genreRepository).save(genre);
        } else {
            doThrow(NullPointerException.class).when(genreRepository).save(genre);
        }

        Genre actual = genreService.create(genre);

        verify(genreRepository, times(1)).save(genre);
        assertEquals(actual, expected);
    }

    @Test
    public void verifyDeleteGenreById() {
        doNothing().when(genreRepository).deleteById(2L);
        genreService.deleteGenreById(2L);
        verify(genreRepository, times(1)).deleteById(2L);
    }

    @Test
    @Parameters(method = "paramsForGetAllGenres")
    public void verifyAllGenres(List<Genre> genres, List<Genre> expectedGenres) {
        when(genreRepository.findAll()).thenReturn(genres);

        List<Genre> actual = genreService.getAllGenres();

        verify(genreRepository, times(1)).findAll();
        assertEquals(actual, expectedGenres);
    }

    @Test
    public void verifyGetGenreById() {
        when(genreRepository.getById(1L)).thenReturn(testDataStorage.genre1);

        Genre actual = genreService.getGenreById(1L);

        verify(genreRepository, times(1)).getById(1L);
        assertEquals(actual, testDataStorage.genre1);
    }

    @Test
    @Parameters(method = "paramsNameGenreAndEntityGenre")
    public void verifyGetGenreByName(String name, Genre expectedGenre) {
        when(genreRepository.getGenreByName(name)).thenReturn(expectedGenre);

        Genre actual = genreService.getGenreByName(name);

        verify(genreRepository, times(1)).getGenreByName(name);
        assertEquals(actual, expectedGenre);
    }

    @Test
    @Parameters(method = "paramsNameGenreAndEntityGenre")
    public void verifyBuildGenreIfGenrePresentInRepository(String name, Genre expectedGenre) {
        when(genreRepository.getGenreByName(name)).thenReturn(expectedGenre);

        Genre actual = genreService.buildGenre(name);

        verify(genreRepository, times(1)).getGenreByName(name);
        verify(genreRepository, times(0)).save(any(Genre.class));
        assertEquals(actual, expectedGenre);
    }

    @Test
    @Parameters(method = "paramsNameGenreAndEntityGenre")
    public void verifyBuildGenreIfGenreNotPresentInRepository(String name, Genre expectedGenre) {
        when(genreRepository.getGenreByName(name)).thenReturn(null);
        when(genreRepository.save(any(Genre.class))).thenReturn(expectedGenre);

        Genre actual = genreService.buildGenre(name);

        verify(genreRepository, times(1)).getGenreByName(name);
        verify(genreRepository, times(1)).save(expectedGenre);
        assertEquals(actual, expectedGenre);
    }




}

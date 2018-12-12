package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IGenreRepository;
import ua.lib_sb.entity.Genre;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;
    @MockBean
    private IGenreRepository genreRepository;

    private Genre genre;

    @Before
    public void setUp() {
        genre = new Genre("aa");
    }

    @Test
    public void findAllGenres() {
        List<Genre> list = Arrays.asList(new Genre(), new Genre(), new Genre());
        when(genreRepository.findAll()).thenReturn(list);
        List<Genre> result = genreService.findAllGenres();
        assertEquals(3, result.size());
    }

    @Test
    public void findGenreByName() {
        when(genreRepository.findByName("aa")).thenReturn(genre);
        Genre result = genreService.findGenreByName("aa");
        assertEquals(genre, result);
    }

    @Test
    public void saveGenre() {
        genreService.saveGenre(genre);
        verify(genreRepository, times(1)).save(genre);
    }
}
package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IAuthorRepository;
import ua.lib_sb.entity.Author;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;
    @MockBean
    private IAuthorRepository authorRepository;

    private Author author;

    @Before
    public void setUp() {
        author = new Author();
        author.setName("Venia");
    }

    @Test
    public void findAuthorByName() {
        when(authorService.findAuthorByName("Venia")).thenReturn(author);
        Author result = authorRepository.findByName("Venia");
        assertEquals("Venia", result.getName());
    }

    @Test
    public void saveAuthor() {

        authorService.saveAuthor(author);
        verify(authorRepository, times(1)).save(author);
    }
}
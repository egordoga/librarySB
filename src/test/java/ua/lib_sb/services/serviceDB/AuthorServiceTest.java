package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ua.lib_sb.dao.IAuthorRepository;
import ua.lib_sb.entity.Author;
import ua.lib_sb.entity.User;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthorServiceTest {

    private AuthorService authorService;
    private Author author = new Author();

    @Mock
    private IAuthorRepository mockAuthorRepo;

    @Before
    public void setUp() {
        initMocks(this);
        authorService = new AuthorService(mockAuthorRepo);
        author.setName("Pushkin");

        when(authorService.findAuthorByName(anyString())).thenReturn(author);
        when(authorService.saveAuthor(any())).thenReturn(author);
    }

    @Test
    public void findAuthorByName() {
        Author result = authorService.findAuthorByName("Pushkin");
        assertEquals("Pushkin", result.getName());
    }

    @Test
    public void saveAuthor() {
        Author result = authorService.saveAuthor(author);
        assertEquals("Pushkin", result.getName());
    }
}
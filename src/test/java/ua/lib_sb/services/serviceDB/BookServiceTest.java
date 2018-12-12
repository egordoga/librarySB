package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IBookRepository;
import ua.lib_sb.entity.Book;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @MockBean
    private IBookRepository bookRepository;

    private Book book;
    private Pageable pageable;
    private Page<Book> page;

    @Before
    public void setUp() {
        book = new Book();
        pageable = PageRequest.of(1, 2);
        page = new PageImpl<>(new ArrayList<>());
    }

    @Test
    public void saveBook() {
        bookService.saveBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void findAllBook() {
        bookService.findAllBook(pageable);
        verify(bookRepository, times(1)).findAll(pageable);

        when(bookRepository.findAll(pageable)).thenReturn(page);
        Page<Book> result = bookService.findAllBook(pageable);
        assertEquals(page, result);
    }

    @Test
    public void findBooksByGenreId() {
        when(bookService.findBooksByGenreId(1L, pageable)).thenReturn(page);
        Page<Book> result = bookRepository.findAllByGenre_Id(1L, pageable);
        assertEquals(page, result);
    }

    @Test
    public void findBookById() {
        when(bookService.findBookById(1L)).thenReturn(book);
        Book result = bookRepository.getOne(1L);
        assertEquals(book, result);
    }

    @Test
    public void findBooksByNameStartsWith() {
        when(bookService.findBooksByNameStartsWith("a", pageable)).thenReturn(page);
        Page<Book> result = bookRepository.findAllByNameStartsWith("a", pageable);
        assertEquals(page, result);
    }

    @Test
    public void findBooksByAuthor_NameStartsWith() {
        when(bookService.findBooksByAuthor_NameStartsWith("a", pageable)).thenReturn(page);
        Page<Book> result = bookRepository.findAllByAuthor_NameStartsWith("a", pageable);
        assertEquals(page, result);
    }

    @Test
    public void findBooksByNameContains() {
        when(bookService.findBooksByNameContains("a", pageable)).thenReturn(page);
        Page<Book> result = bookRepository.findAllByNameContains("a", pageable);
        assertEquals(page, result);
    }

    @Test
    public void findBooksByAuthor_NameContains() {
        when(bookService.findBooksByAuthor_NameContains("a", pageable)).thenReturn(page);
        Page<Book> result = bookRepository.findAllByAuthor_NameContains("a", pageable);
        assertEquals(page, result);
    }
}
package ua.lib_sb.services.serviceDB;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.lib_sb.entity.Book;

public interface IBookService {
    void saveBook(Book book);

    Page<Book> findAllBook(Pageable pageable);

    Page<Book> findBooksByGenreId(Long id, Pageable pageable);

    Book findBookById(Long id);

    Page<Book> findBooksByNameStartsWith(String letter, Pageable pageable);

    Page<Book> findBooksByAuthor_NameStartsWith(String letter, Pageable pageable);

    Page<Book> findBooksByNameContains(String search, Pageable pageable);

    Page<Book> findBooksByAuthor_NameContains(String search, Pageable pageable);
}

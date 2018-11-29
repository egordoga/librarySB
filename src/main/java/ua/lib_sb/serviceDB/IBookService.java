package ua.lib_sb.serviceDB;

import ua.lib_sb.entity.Book;

import java.util.List;

public interface IBookService {
    void saveBook(Book book);

    List<Book> findAllBook();

    List<Book> findBooksByGenreId(Long id);

    Book findBookById(Long id);
}

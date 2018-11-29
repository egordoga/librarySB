package ua.lib_sb.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IBookRepository;
import ua.lib_sb.entity.Book;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksByGenreId(Long id) {
        return bookRepository.findAllByGenre_Id(id);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.getOne(id);
    }
}

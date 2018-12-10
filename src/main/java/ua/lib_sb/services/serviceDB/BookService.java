package ua.lib_sb.services.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IBookRepository;
import ua.lib_sb.entity.Book;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findBooksByGenreId(Long id, Pageable pageable) {
        return bookRepository.findAllByGenre_Id(id, pageable);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public Page<Book> findBooksByNameStartsWith(String letter, Pageable pageable) {
        return bookRepository.findAllByNameStartsWith(letter, pageable);
    }

    @Override
    public Page<Book> findBooksByAuthor_NameStartsWith(String letter, Pageable pageable) {
        return bookRepository.findAllByAuthor_NameStartsWith(letter, pageable);
    }

    @Override
    public Page<Book> findBooksByNameContains(String search, Pageable pageable) {
        return bookRepository.findAllByNameContains(search, pageable);
    }

    @Override
    public Page<Book> findBooksByAuthor_NameContains(String search, Pageable pageable) {
        return bookRepository.findAllByAuthor_NameContains(search, pageable);
    }
}

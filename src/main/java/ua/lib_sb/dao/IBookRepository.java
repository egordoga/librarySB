package ua.lib_sb.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.lib_sb.entity.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByNameStartsWith(String letter, Pageable pageable);

    Page<Book> findAllByAuthor_NameStartsWith(String letter, Pageable pageable);

    Page<Book> findAllByNameContains(String search, Pageable pageable);

    Page<Book> findAllByAuthor_NameContains(String search, Pageable pageable);

    Page<Book> findAllByGenre_Id(Long id, Pageable pageable);
}

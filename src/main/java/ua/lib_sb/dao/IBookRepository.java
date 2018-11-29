package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lib_sb.entity.Book;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByGenre_Id(Long id);
}

package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lib_sb.entity.Author;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}

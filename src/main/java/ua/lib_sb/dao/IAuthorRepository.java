package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lib_sb.entity.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}

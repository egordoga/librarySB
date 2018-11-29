package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lib_sb.entity.Genre;

public interface IGenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
}

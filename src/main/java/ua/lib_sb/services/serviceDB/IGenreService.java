package ua.lib_sb.services.serviceDB;

import ua.lib_sb.entity.Genre;

import java.util.List;

public interface IGenreService {
    List<Genre> findAllGenres();

    Genre findGenreByName(String name);

    void saveGenre(Genre genre);
}

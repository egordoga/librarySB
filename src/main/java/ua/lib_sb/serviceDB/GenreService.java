package ua.lib_sb.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IGenreRepository;
import ua.lib_sb.entity.Genre;

import java.util.List;

@Service
public class GenreService implements IGenreService{
    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }
}

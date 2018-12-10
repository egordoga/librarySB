package ua.lib_sb.services.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IAuthorRepository;
import ua.lib_sb.entity.Author;

@Service
public class AuthorService implements IAuthorService {
    private final IAuthorRepository authorRepository;

    @Autowired
    public AuthorService(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public void saveAuthor(Author author) {
       authorRepository.save(author);
    }
}

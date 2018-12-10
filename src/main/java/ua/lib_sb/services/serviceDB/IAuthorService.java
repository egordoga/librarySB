package ua.lib_sb.services.serviceDB;

import ua.lib_sb.entity.Author;

public interface IAuthorService {
    Author findAuthorByName(String name);

    Author saveAuthor(Author author);
}

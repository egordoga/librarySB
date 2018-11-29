package ua.lib_sb.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lib_sb.entity.*;
import ua.lib_sb.model.BookForm;
import ua.lib_sb.serviceDB.*;

import java.io.IOException;

@Controller
public class AddController {

    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private BookService bookService;

    //private BookForm bookForm = new BookForm();

    @GetMapping("/add_book")
    public String viewAddBook(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "add_book";
    }

    @PostMapping("/add_book")
    public String addBook(Model model, @ModelAttribute BookForm bookForm) {
        model.addAttribute("bookForm", bookForm);

        Book book = new Book();
        Author authorFromDB = authorService.findAuthorByName(bookForm.getAuthor());
        Publisher publisherFromDB = publisherService.findPublisherByName(bookForm.getPublisher());
        Genre genreFromDB = genreService.findGenreByName(bookForm.getGenre());
        if (authorFromDB != null) {
            book.setAuthor(authorFromDB);
        } else {
            Author author = new Author(bookForm.getAuthor());
            authorService.saveAuthor(author);
            book.setAuthor(author);
        }

        if (publisherFromDB != null) {
            book.setPublisher(publisherFromDB);
        } else {
            Publisher publisher = new Publisher(bookForm.getPublisher());
            publisherService.savePublisher(publisher);
            book.setPublisher(publisher);
        }

        if (genreFromDB != null) {
            book.setGenre(genreFromDB);
        } else {
            Genre genre = new Genre(bookForm.getGenre());
            genreService.saveGenre(genre);
            book.setGenre(genre);
        }

        book.setName(bookForm.getName());
        book.setPageCount(bookForm.getPageCount());
        book.setPublisherYear(bookForm.getPublisherYear());
        book.setDescription(bookForm.getDescription());
        book.setIsbn(bookForm.getIsbn());

        try {
            book.setPicture(ArrayUtils.toObject(bookForm.getPicture().getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Content content;
        try {
            content = new Content(ArrayUtils.toObject(bookForm.getContent().getBytes()));
            contentService.saveContent(content);
            book.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookService.saveBook(book);
        return "redirect:/add_book";
    }
}

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
import ua.lib_sb.services.serviceDB.*;

import java.io.IOException;

@Controller
public class EditController {

    private IBookService bookService;
    private IAuthorService authorService;
    private IPublisherService publisherService;
    private IGenreService genreService;
    private ContentService contentService;

    @Autowired
    public EditController(BookService bookService, AuthorService authorService, PublisherService publisherService,
                          GenreService genreService, ContentService contentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.genreService = genreService;
        this.contentService = contentService;
    }

    @GetMapping("/edit_book")
    public String viewEditBook(@ModelAttribute("bId") Book book, Model model) {
        BookForm bookForm = new BookForm(book.getName(), book.getIsbn(), book.getPublisherYear(), book.getPageCount(),
                book.getDescription(), book.getAuthor().getName(), book.getGenre().getName(), book.getPublisher().getName());

        model.addAttribute("bookForm", bookForm);
        model.addAttribute("book", book);
        ;
        return "edit_book";
    }

    @PostMapping("/edit_book")
    public String addBook(Model model, @ModelAttribute("bId") Long bId, @ModelAttribute BookForm bookForm) {
        model.addAttribute("bookForm", bookForm);

        Book book = bookService.findBookById(bId);
        saveFromForm(book, bookForm);
        return "redirect:/main";
    }

    void saveFromForm(Book book, BookForm bookForm) {
        Author authorFromDB = authorService.findAuthorByName(bookForm.getAuthor());
        Publisher publisherFromDB = publisherService.findPublisherByName(bookForm.getPublisher());
        Genre genreFromDB = genreService.findGenreByName(bookForm.getGenre());

        if (publisherFromDB != null) {
            book.setPublisher(publisherFromDB);
        } else {
            Publisher publisher = new Publisher(bookForm.getPublisher());
            publisherService.savePublisher(publisher);
            book.setPublisher(publisher);
        }

        if (authorFromDB != null) {
            book.setAuthor(authorFromDB);
        } else {
            Author author = new Author(bookForm.getAuthor());
            authorService.saveAuthor(author);
            book.setAuthor(author);
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
            Byte[] picture = ArrayUtils.toObject(bookForm.getPicture().getBytes());
            if (picture.length > 0) {
                book.setPicture(picture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Content content;
        try {
            Byte[] cont = ArrayUtils.toObject(bookForm.getContent().getBytes());
            if (cont.length > 0) {
                content = new Content(cont);
                contentService.saveContent(content);
                book.setContent(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookService.saveBook(book);
    }
}

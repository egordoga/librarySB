package ua.lib_sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lib_sb.entity.Book;
import ua.lib_sb.model.BookForm;
import ua.lib_sb.services.serviceDB.*;

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

    @GetMapping("/add_book")
    public String viewAddBook(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "add_book";
    }

    @PostMapping("/add_book")
    public String addBook(Model model, @ModelAttribute BookForm bookForm) {
        model.addAttribute("bookForm", bookForm);
        Book book = new Book();
        EditController ec = new EditController(bookService, authorService, publisherService, genreService, contentService);
        ec.saveFromForm(book, bookForm);

        return "redirect:/add_book";
    }
}

package ua.lib_sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lib_sb.entity.*;
import ua.lib_sb.model.TestForm;
import ua.lib_sb.services.serviceDB.BookService;

import java.io.IOException;
import java.util.Arrays;

@Controller
public class TestController {

    @Autowired
    private BookService bookService;

    @GetMapping("/test")
    public String viewEditBook(@ModelAttribute("bId") Book book, Model model) {
        //Book book = bookService.findBookById(bookId);
        TestForm testForm = new TestForm(book.getName(), book.getIsbn(), book.getPublisherYear(), book.getPageCount(),
                book.getDescription(), book.getAuthor().getName(), book.getGenre().getName(), book.getPublisher().getName());

        model.addAttribute("testForm", testForm);
        model.addAttribute("book", book);
        return "test";
    }


    @PostMapping("/test")
    public String addBook(Model model, @ModelAttribute TestForm testForm) throws IOException {
        model.addAttribute("testForm", testForm);

        System.out.println("This value: " + testForm.getName());
        System.out.println("This value: " + testForm.getAuthorName());
        System.out.println("This value: " + Arrays.toString(testForm.getPicture().getBytes()));
        System.out.println("sizePict = " + testForm.getPicture().getBytes().length);
        System.out.println("sizeCont = " + testForm.getContent().getBytes().length);

        return "redirect:/main";
    }
}

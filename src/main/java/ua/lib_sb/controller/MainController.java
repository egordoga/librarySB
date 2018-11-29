package ua.lib_sb.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.lib_sb.entity.Book;
import ua.lib_sb.model.RussianLetter;
import ua.lib_sb.serviceDB.BookService;
import ua.lib_sb.serviceDB.GenreService;

@Controller
public class MainController {

    @Autowired
    private GenreService genreService;
    @Autowired
    private BookService bookService;

    @GetMapping("/main")
    public String viewMain(Model model) {
        model.addAttribute("letters", RussianLetter.getRusLets());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("books", bookService.findAllBook());
        return "main";
    }

    @GetMapping("/pict")
    public @ResponseBody
    byte[] showPicture(@ModelAttribute("pictId") String strId) {

        System.out.println("Str " + strId);

        Book book = bookService.findBookById(Long.parseLong(strId));
        return ArrayUtils.toPrimitive(book.getPicture());
    }
}

package ua.lib_sb.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.lib_sb.entity.Book;
import ua.lib_sb.entity.Role;
import ua.lib_sb.entity.User;
import ua.lib_sb.model.RussianLetter;
import ua.lib_sb.services.serviceDB.BookService;
import ua.lib_sb.services.serviceDB.GenreService;
import ua.lib_sb.services.serviceDB.RoleService;
import ua.lib_sb.services.serviceDB.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @Autowired
    private GenreService genreService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/main")
    public String viewMain(Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                           Principal principal) {
        model.addAttribute("letters", RussianLetter.getRusLets());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("url", "/main");

        User user;
        Role role;
        if (principal != null) {
            user = userService.findUserByEmail(principal.getName());
            role = roleService.findRoleByName("admin");
            if (user.getRoles().contains(role)) {
                model.addAttribute("isAdmin", true);
            }
        }

        Page<Book> books = bookService.findAllBook(pageable);
        model.addAttribute("books", books);

        List pages = IntStream.range(0, books.getTotalPages()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        model.addAttribute("pages", pages);
        return "main";
    }

    @GetMapping("/pict")
    public @ResponseBody
    byte[] showPicture(@ModelAttribute("pictId") Long id) {
        Book book = bookService.findBookById(id);
        return ArrayUtils.toPrimitive(book.getPicture());
    }

    @GetMapping("/content")
    public void showContent(@ModelAttribute("bId") Long id, HttpServletResponse response) {
        Book book = bookService.findBookById(id);

        try (OutputStream out = response.getOutputStream()) {
            response.setContentType("application/pdf");
            byte[] content = ArrayUtils.toPrimitive(book.getContent().getContent());
            response.setContentLength(content.length);
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/byGenre")
    public String showByGenre(@ModelAttribute("genId") Long gId, Model model,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("letters", RussianLetter.getRusLets());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("url", "/byGenre?genId=" + gId);
        Page<Book> books = bookService.findBooksByGenreId(gId, pageable);
        model.addAttribute("books", books);
        List pages = IntStream.range(0, books.getTotalPages()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        model.addAttribute("pages", pages);

        return "main";
    }

    @GetMapping("/byLetter")
    public String showByLetter(@ModelAttribute("let") String letter, Model model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("letters", RussianLetter.getRusLets());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("url", "/byLetter?let=" + letter);
        Page<Book> books = bookService.findBooksByNameStartsWith(letter, pageable);
        model.addAttribute("books", books);
        List pages = IntStream.range(0, books.getTotalPages()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        model.addAttribute("pages", pages);

        return "main";
    }

    @GetMapping("/bySearch")
    public String showBySearch(@ModelAttribute("srchStr") String srchStr, @ModelAttribute("select") String select, Model model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("letters", RussianLetter.getRusLets());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("url", "/bySearch?select=" + select + "&srchStr=" + srchStr);

        Page<Book> books = null;
        switch (select) {
            case "name":
                books = bookService.findBooksByNameContains(srchStr, pageable);
                break;
            case "author":
                books = bookService.findBooksByAuthor_NameContains(srchStr, pageable);
        }
        assert books != null;
        model.addAttribute("books", books);
        List pages = IntStream.range(0, books.getTotalPages()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        model.addAttribute("pages", pages);

        return "main";
    }
}

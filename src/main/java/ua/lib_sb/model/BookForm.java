package ua.lib_sb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class BookForm {

    private String name;
    private String isbn;
    private int publisherYear;
    private int pageCount;
    private String description;
    private MultipartFile picture;
    private MultipartFile content;
    private String author;
    private String genre;
    private String publisher;

    public BookForm(String name, String isbn, int publisherYear, int pageCount, String description, String author, String genre, String publisher) {
        this.name = name;
        this.isbn = isbn;
        this.publisherYear = publisherYear;
        this.pageCount = pageCount;
        this.description = description;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }
}

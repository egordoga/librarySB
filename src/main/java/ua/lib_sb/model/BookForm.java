package ua.lib_sb.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
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
}

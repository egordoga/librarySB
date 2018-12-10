package ua.lib_sb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class TestForm {

    private String name;
    private String isbn;
    private int publisherYear;
    private int pageCount;
    private String description;
    private MultipartFile picture;
    private MultipartFile content;
    private String authorName;
    private String genreName;
    private String publisherName;

    public TestForm(String name, String isbn, int publisherYear, int pageCount, String description, String authorName, String genreName, String publisherName) {
        this.name = name;
        this.isbn = isbn;
        this.publisherYear = publisherYear;
        this.pageCount = pageCount;
        this.description = description;
        this.authorName = authorName;
        this.genreName = genreName;
        this.publisherName = publisherName;
    }
}

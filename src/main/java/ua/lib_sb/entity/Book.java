package ua.lib_sb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String isbn;
    private Integer publisherYear;
    private Integer pageCount;

    @Column(length = 512)
    private String description;

    @Lob
    private Byte[] picture;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private Content content;
}

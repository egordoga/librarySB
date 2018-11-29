package ua.lib_sb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private Byte[] content;

    @OneToOne(mappedBy = "content")
    private Book book;

    public Content(Byte[] content) {
        this.content = content;
    }
}

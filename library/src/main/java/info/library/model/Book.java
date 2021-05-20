package info.library.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Book {

    private Long id;
    private String title;
    private Author author;
    private Genre genre;
    private String ISBN;

}

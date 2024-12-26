package org.bookservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode(of = "isbn")
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
    @Id
    String isbn;
    @Column(name = "TITLE")
    String title;
    @ManyToMany
        @JoinTable(
                name = "BOOK_AUTHORS",
                joinColumns = @JoinColumn(name = "BOOK_ISBN"),
                inverseJoinColumns = @JoinColumn(name = "AUTHORS_NAME")
        )
    Set<Author> authors;
    @ManyToOne
    Publisher publisher;
}

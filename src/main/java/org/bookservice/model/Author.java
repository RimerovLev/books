package org.bookservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode(of ="name")
@Entity
public class Author implements Serializable {
    @Id
    String name;
    LocalDate birthDate;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
    Set<Book> books;

    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}

package org.bookservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
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
}

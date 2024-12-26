package org.bookservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.io.Serializable;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@EqualsAndHashCode(of = "publisherName")
public class Publisher implements Serializable {
    @Id
    String publisherName;

    @Override
    public String toString() {
        return publisherName;
    }
}

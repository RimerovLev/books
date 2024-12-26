package org.bookservice.dao;

import org.bookservice.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
//    @Query("select distinct p.publisherName from Book b join b.authors a join b.publisher p where a.name=?1")
//    List<String> findByPublishersAuthor(String authorName);

    List<Publisher> findDistinctByBooksAuthorsName(String authorsName);
}

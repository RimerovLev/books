package org.bookservice.dao;

import org.bookservice.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
    @Query("select distinct p.publisherName from Book b join b.authors a join b.publisher p where a.name=?1")
    List<String> findByPublishersAuthor(String authorName);
}

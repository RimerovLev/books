package org.bookservice.dao;

import org.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, String> {
    Stream<Book> findBooksByAuthorsName(String name);

    List<Book> findByPublisherPublisherName(String publisherName);

    void deleteByAuthorsName(String authorName);


}

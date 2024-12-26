package org.bookservice.service;

import org.bookservice.dto.AuthorDto;
import org.bookservice.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface BookService {
    boolean addBook(BookDto bookDto);

    BookDto findBookByIsbn(String isbn);

    BookDto removeBookByIsbn(String isbn);

    BookDto updateTitleByIsbn(String isbn, String title);

    List<BookDto> findBooksByAuthorsName(String name);

    List<BookDto> findBooksByPublisherName(String publisherName);

    List<AuthorDto> findAuthorsByIsbn(String isbn);

    List<String> findPublishersByAuthor(String author);

    AuthorDto removeAuthor(String authorName);
}

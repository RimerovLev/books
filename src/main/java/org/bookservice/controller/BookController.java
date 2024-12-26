package org.bookservice.controller;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.DeclareError;
import org.bookservice.dto.AuthorDto;
import org.bookservice.dto.BookDto;
import org.bookservice.model.Publisher;
import org.bookservice.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BookController {

    final BookService bookService;


    @PostMapping("/book")
    public boolean addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping("/book/{isbn}")
    public BookDto findBokByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @DeleteMapping("/book/{isbn}")
    public BookDto deleteBook(@PathVariable("isbn") String isbn) {
        return bookService.removeBookByIsbn(isbn);
    }

    @PutMapping("/book/{isbn}/title/{title}")
    public BookDto updateBookTitle(@PathVariable String isbn, @PathVariable String title) {
        return bookService.updateTitleByIsbn(isbn, title);
    }

    @GetMapping("/books/author/{author}")
    public Iterable<BookDto> findBooksByAuthorsName(@PathVariable String author) {
        return bookService.findBooksByAuthorsName(author);
    }

    @GetMapping("/books/publisher/{publisherName}")
    public List<BookDto> findBooksByPublisherName(@PathVariable String publisherName) {
        return bookService.findBooksByPublisherName(publisherName);
    }

    @GetMapping("/authors/book/{isbn}")
    public List<AuthorDto> findAuthorsByIsbn(@PathVariable String isbn) {
        return bookService.findAuthorsByIsbn(isbn);
    }

    @GetMapping("/publishers/author/{author}")
    public List<String> findPublisherByAuthor(@PathVariable String author) {
        return bookService.findPublishersByAuthor(author);
    }


    @DeleteMapping("/author/{author}")
    public AuthorDto removeAuthor(@PathVariable String author) {
        return bookService.removeAuthor(author);
    }

}

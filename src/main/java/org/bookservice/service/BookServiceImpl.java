package org.bookservice.service;

import lombok.RequiredArgsConstructor;
import org.bookservice.dao.AuthorRepository;
import org.bookservice.dao.BookRepository;
import org.bookservice.dao.PublisherRepository;
import org.bookservice.dto.AuthorDto;
import org.bookservice.dto.BookDto;
import org.bookservice.dto.exceptions.EntityNotFoundException;
import org.bookservice.model.Author;
import org.bookservice.model.Book;
import org.bookservice.model.Publisher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final ModelMapper modelMapper;
    final BookRepository bookRepository;
    final PublisherRepository publisherRepository;
    final AuthorRepository authorRepository;

    @Transactional
    @Override
    public boolean addBook(BookDto bookDto) {
        if(bookRepository.existsById(bookDto.getIsbn())) {
            return false;
        }
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
                .orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getName())
                        .orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
                .collect(Collectors.toSet());
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(),authors, publisher);
        bookRepository.save(book);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto removeBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        bookRepository.delete(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    public BookDto updateTitleByIsbn(String isbn, String title) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(title);
        return modelMapper.map(book, BookDto.class);
    }


    public List<BookDto> findBooksByAuthorsName(String name) {
        Author author = authorRepository.findById(name).orElseThrow(EntityNotFoundException::new);
        return author.getBooks().stream()
                .map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookDto> findBooksByPublisherName(String publisherName) {
        Publisher publisher = publisherRepository.findById(publisherName).orElseThrow(EntityNotFoundException::new);
        return publisher.getBooks().stream()
                .map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> findAuthorsByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return book.getAuthors().stream()
                .map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findPublishersByAuthor(String author) {
        return publisherRepository.findDistinctByBooksAuthorsName(author)
                .stream().map(Publisher::getPublisherName).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorDto removeAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundException::new);
        authorRepository.deleteById(authorName);
        return modelMapper.map(author, AuthorDto.class);
    }


}

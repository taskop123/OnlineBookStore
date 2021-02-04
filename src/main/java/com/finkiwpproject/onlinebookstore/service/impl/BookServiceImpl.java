package com.finkiwpproject.onlinebookstore.service.impl;

import com.finkiwpproject.onlinebookstore.model.Book;
import com.finkiwpproject.onlinebookstore.model.Category;
import com.finkiwpproject.onlinebookstore.model.exceptions.BookNotFoundException;
import com.finkiwpproject.onlinebookstore.repository.BookRepository;
import com.finkiwpproject.onlinebookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return Optional.of(this.bookRepository.findBookByTitleEquals(title));
    }

    @Override
    public List<Book> findAllByCategory(Category c) {
        /*TODO:
        *  ...
        * */
        return null;
    }

    @Override
    public Optional<Book> create(String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price, Category category) {
        if (title == null || title.isEmpty() || dateOfIssue == null || numberOfSamples == 0 || imageURL == null || imageURL.isEmpty() || category == null){
            throw new IllegalArgumentException();
        }
        Book book = new Book(title, numberOfPages, dateOfIssue, numberOfSamples, imageURL, price, category);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price, Category category) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        book.setDateOfIssue(dateOfIssue);
        book.setNumberOfSamples(numberOfSamples);
        book.setPrice(price);
        book.setCategory(category);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Book deleteById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
        return book;
    }
}

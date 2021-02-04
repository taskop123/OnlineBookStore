package com.finkiwpproject.onlinebookstore.service.impl;

import com.finkiwpproject.onlinebookstore.model.Author;
import com.finkiwpproject.onlinebookstore.model.Book;
import com.finkiwpproject.onlinebookstore.model.Category;
import com.finkiwpproject.onlinebookstore.model.exceptions.BookNotFoundException;
import com.finkiwpproject.onlinebookstore.model.exceptions.CategoryNotFoundException;
import com.finkiwpproject.onlinebookstore.model.exceptions.NotEnoughSamplesException;
import com.finkiwpproject.onlinebookstore.repository.AuthorRepository;
import com.finkiwpproject.onlinebookstore.repository.BookRepository;
import com.finkiwpproject.onlinebookstore.repository.CategoryRepository;
import com.finkiwpproject.onlinebookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
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
        return this.bookRepository.findBooksByCategory(c);
    }

    @Override
    public Optional<Book> create(String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price, Long categoryId, List<Long> authorsId) {
        if (title == null || title.isEmpty() || dateOfIssue == null || numberOfSamples == 0 || imageURL == null || imageURL.isEmpty() || authorsId.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        List<Author> authors = this.authorRepository.findAllById(authorsId);
        Book book = new Book(title, numberOfPages, dateOfIssue, numberOfSamples, imageURL, price, category, authors);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price,  Long categoryId, List<Long> authorsId) {
        if (title == null || title.isEmpty() || dateOfIssue == null || numberOfSamples == 0 || imageURL == null || imageURL.isEmpty() || authorsId.isEmpty()){
            throw new IllegalArgumentException();
        }

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(id));
        List<Author> authors = this.authorRepository.findAllById(authorsId);

        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        book.setDateOfIssue(dateOfIssue);
        book.setNumberOfSamples(numberOfSamples);
        book.setPrice(price);
        book.setCategory(category);
        book.setAuthors(authors);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Book deleteById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
        return book;
    }

    @Override
    public int sellBooks(Long id, int howMuch) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        int booksLeft = book.getNumberOfSamples() - howMuch;
        if (booksLeft >= 0){
            book.setNumberOfSamples(booksLeft);
            this.bookRepository.save(book);
            return booksLeft;
        }else{
            throw new NotEnoughSamplesException(howMuch, book.getTitle());
        }
    }

    @Override
    public int addBooks(Long id, int howMuch) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        int newNumberOfSamples = book.getNumberOfSamples() + howMuch;
        book.setNumberOfSamples(newNumberOfSamples);
        this.bookRepository.save(book);
        return newNumberOfSamples;
    }
}

package com.finkiwpproject.onlinebookstore.service.impl;

import com.finkiwpproject.onlinebookstore.model.Author;
import com.finkiwpproject.onlinebookstore.model.Book;
import com.finkiwpproject.onlinebookstore.model.exceptions.AuthorNotFoundException;
import com.finkiwpproject.onlinebookstore.repository.AuthorRepository;
import com.finkiwpproject.onlinebookstore.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(String name, String surname, LocalDate dateOfBirth, String biographyURL) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || dateOfBirth == null || biographyURL == null || biographyURL.isEmpty()){
            throw new IllegalArgumentException();
        }
        return Optional.of(this.authorRepository.save(new Author(name, surname, dateOfBirth, biographyURL)));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, LocalDate dateOfBirth, String biographyURL) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurname(surname);
        author.setDateOfBirth(dateOfBirth);
        author.setBiographyURL(biographyURL);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public List<Book> findAllBooksByAuthorName(String name, String surname) {

        Author author = this.authorRepository.findAuthorByNameEqualsAndSurnameEquals(name, surname);
        return author.getBooks();

    }

    @Override
    public Author delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        this.authorRepository.delete(author);
        return author;
    }
}

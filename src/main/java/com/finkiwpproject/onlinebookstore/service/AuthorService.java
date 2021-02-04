package com.finkiwpproject.onlinebookstore.service;

import com.finkiwpproject.onlinebookstore.model.Author;
import com.finkiwpproject.onlinebookstore.model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(String name, String surname, LocalDate dateOfBirth, String biographyURL);

    Optional<Author> edit(Long id, String name, String surname, LocalDate dateOfBirth, String biographyURL);

    List<Book> findAllBooksByAuthorName(String name, String surname);

    Author delete(Long id);

}

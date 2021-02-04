package com.finkiwpproject.onlinebookstore.service;

import com.finkiwpproject.onlinebookstore.model.Book;
import com.finkiwpproject.onlinebookstore.model.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    List<Book> findAllByCategory(Category c);
    Optional<Book> create(String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price, Category category);
    Optional<Book> edit(Long id, String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price, Category category);
    Book deleteById(Long id);

}

package com.finkiwpproject.onlinebookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String biographyURL;
    @ManyToMany
    private List<Book> books;

    public Author(){

    }

    public Author(String name, String surname, LocalDate dateOfBirth, String biographyURL) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.biographyURL = biographyURL;
        books = new ArrayList<>();
    }
}

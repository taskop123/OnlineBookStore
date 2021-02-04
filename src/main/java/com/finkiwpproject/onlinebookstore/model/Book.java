package com.finkiwpproject.onlinebookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int numberOfPages;

    private LocalDateTime dateOfIssue;

    private int numberOfSamples;

    private String imageURL;

    private float price;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Author> authors;

    public Book(){

    }

    public Book(String title, int numberOfPages, LocalDateTime dateOfIssue, int numberOfSamples, String imageURL, float price, Category category){

        this.title = title;
        this.numberOfPages = numberOfPages;
        this.dateOfIssue = dateOfIssue;
        this.numberOfSamples = numberOfSamples;
        this.imageURL = imageURL;
        this.price = price;
        this.category = category;
        authors = new ArrayList<>();

    }

}

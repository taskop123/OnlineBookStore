package com.finkiwpproject.onlinebookstore.model;

import com.finkiwpproject.onlinebookstore.model.enumerations.ShoppingCartStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    private ShoppingCartStatus status;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Book> books;

    public ShoppingCart(){

    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.status = ShoppingCartStatus.CREATED;
        this.user = user;

        books = new ArrayList<>();

    }
}

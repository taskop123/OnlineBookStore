package com.finkiwpproject.onlinebookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category(){

    }

    public Category(String name){
        this.name = name;
    }

}

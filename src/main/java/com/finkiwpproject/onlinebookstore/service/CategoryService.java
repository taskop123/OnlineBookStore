package com.finkiwpproject.onlinebookstore.service;

import com.finkiwpproject.onlinebookstore.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();
    Optional<Category> create(String name);
    Optional<Category> edit(Long id, String name);
    Category delete(Long id);

}

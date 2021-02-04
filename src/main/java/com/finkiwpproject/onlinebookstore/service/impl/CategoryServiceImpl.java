package com.finkiwpproject.onlinebookstore.service.impl;

import com.finkiwpproject.onlinebookstore.model.Category;
import com.finkiwpproject.onlinebookstore.model.exceptions.CategoryNotFoundException;
import com.finkiwpproject.onlinebookstore.repository.CategoryRepository;
import com.finkiwpproject.onlinebookstore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> create(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        return Optional.of(this.categoryRepository.save(new Category(name)));
    }

    @Override
    public Optional<Category> edit(Long id, String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        category.setName(name);
        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public Category delete(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        this.categoryRepository.delete(category);
        return category;
    }
}

package com.finkiwpproject.onlinebookstore.repository;

import com.finkiwpproject.onlinebookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

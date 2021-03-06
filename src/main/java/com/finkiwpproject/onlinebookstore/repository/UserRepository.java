package com.finkiwpproject.onlinebookstore.repository;

import com.finkiwpproject.onlinebookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

package com.finkiwpproject.onlinebookstore.repository;

import com.finkiwpproject.onlinebookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}

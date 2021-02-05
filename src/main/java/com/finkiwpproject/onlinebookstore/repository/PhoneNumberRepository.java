package com.finkiwpproject.onlinebookstore.repository;

import com.finkiwpproject.onlinebookstore.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}

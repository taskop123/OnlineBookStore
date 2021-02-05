package com.finkiwpproject.onlinebookstore.service;

import com.finkiwpproject.onlinebookstore.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberService {

    List<PhoneNumber> findAll();
    Optional<PhoneNumber> create(String phoneNumber, String username);
    Optional<PhoneNumber> edit(Long id, String phoneNumber);
    PhoneNumber delete(Long id);

}

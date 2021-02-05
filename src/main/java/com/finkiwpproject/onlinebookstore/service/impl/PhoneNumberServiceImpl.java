package com.finkiwpproject.onlinebookstore.service.impl;

import com.finkiwpproject.onlinebookstore.model.PhoneNumber;
import com.finkiwpproject.onlinebookstore.model.User;
import com.finkiwpproject.onlinebookstore.model.exceptions.PhoneNumberNotFound;
import com.finkiwpproject.onlinebookstore.model.exceptions.UserNotFoundException;
import com.finkiwpproject.onlinebookstore.repository.PhoneNumberRepository;
import com.finkiwpproject.onlinebookstore.repository.UserRepository;
import com.finkiwpproject.onlinebookstore.service.PhoneNumberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final UserRepository userRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository, UserRepository userRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PhoneNumber> findAll() {
        return this.phoneNumberRepository.findAll();
    }

    @Override
    public Optional<PhoneNumber> create(String phoneNumber, String username) {
        User user = this.userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        if (phoneNumber == null || phoneNumber.isEmpty()){
            throw new IllegalArgumentException();
        }
        return Optional.of(this.phoneNumberRepository.save(new PhoneNumber(phoneNumber, user)));
    }

    @Override
    public Optional<PhoneNumber> edit(Long id, String phoneNumber) {

        PhoneNumber entity = this.phoneNumberRepository.findById(id).orElseThrow(() -> new PhoneNumberNotFound(phoneNumber));
        entity.setPhoneNumber(phoneNumber);

        return Optional.of(this.phoneNumberRepository.save(entity));
    }

    @Override
    public PhoneNumber delete(Long id) {

        PhoneNumber phoneNumber = this.phoneNumberRepository.findById(id).orElseThrow(() -> new PhoneNumberNotFound(id));
        this.phoneNumberRepository.delete(phoneNumber);
        return phoneNumber;
    }
}

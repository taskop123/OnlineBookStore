package com.finkiwpproject.onlinebookstore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    @ManyToOne
    private User user;

    public PhoneNumber(){

    }

    public PhoneNumber(String phoneNumber, User user) {
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}

package com.finkiwpproject.onlinebookstore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class PhoneNumber {
    @Id
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

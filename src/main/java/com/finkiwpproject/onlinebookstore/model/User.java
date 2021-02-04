package com.finkiwpproject.onlinebookstore.model;

import com.finkiwpproject.onlinebookstore.model.enumerations.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "project_users")
public class User {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    private String address;

    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<PhoneNumber> phoneNumbers;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ShoppingCart> carts;

    public User(){

    }

    public User(String username, String password, String name, String surname, String address, String email, Role role){

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.role = role;

        phoneNumbers = new ArrayList<>();
        carts = new ArrayList<>();
    }

}

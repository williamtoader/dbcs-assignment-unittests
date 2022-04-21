package com.example.dbcsassignmentunittests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    public User(String username, String firstName, String lastName, String address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Id
    String username;

    private String firstName;
    private String lastName;

    private String address;

    @OneToMany
    List<Cart> carts;
}

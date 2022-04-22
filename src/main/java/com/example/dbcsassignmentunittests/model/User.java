package com.example.dbcsassignmentunittests.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
    @ToString.Exclude
    List<Cart> carts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getAddress().equals(user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getFirstName(), getLastName(), getAddress());
    }
}

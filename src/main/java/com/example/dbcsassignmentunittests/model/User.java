package com.example.dbcsassignmentunittests.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
@JsonIncludeProperties({
        "username",
        "firstName",
        "lastName",
        "address"
})
@JsonIgnoreProperties({
        "carts"
})
public class User implements Comparable<User>{
    @Id
    String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    @ToString.Exclude
    List<Cart> carts;
    private String firstName;
    private String lastName;

    private String address;

    public User(String username, String firstName, String lastName, String address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

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

    @Override
    public int compareTo(User o) {
        return Long.compare(this.carts.size(), o.carts.size());
    }
}

package com.example.dbcsassignmentunittests.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Wishlist {
    public Wishlist(String username, List<ProductAndQuantity> products) {
        this.username = username;
        this.products = products;
    }

    @Id
    String username;

    @OneToOne
    @JoinColumn(name = "username")
    User user;

    @OneToMany
    @JoinColumn(name = "wl_username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<ProductAndQuantity> products;
}
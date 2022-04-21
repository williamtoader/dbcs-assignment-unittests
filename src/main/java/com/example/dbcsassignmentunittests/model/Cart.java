package com.example.dbcsassignmentunittests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart implements Comparable<Cart>{
    public Cart(Long id, User user, List<ProductAndQuantity> productAndQuantityList) {
        this.id = id;
        this.user = user;
        this.productAndQuantityList = productAndQuantityList;
    }

    public Cart(User user, List<ProductAndQuantity> productAndQuantityList) {
        this.user = user;
        this.productAndQuantityList = productAndQuantityList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<ProductAndQuantity> productAndQuantityList;

    @Transient
    private double computedTotal;

    @Override
    public int compareTo(Cart o) {
        return Double.compare(this.getComputedTotal(), o.getComputedTotal());
    }
}

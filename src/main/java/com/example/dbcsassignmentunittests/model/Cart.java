package com.example.dbcsassignmentunittests.model;

import com.example.dbcsassignmentunittests.model.enums.CartStatus;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart implements Comparable<Cart> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    @OneToMany
    @JoinColumn(name = "cart_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    List<ProductAndQuantity> productAndQuantityList;
    @Transient
    private double computedTotal;
    private CartStatus status = CartStatus.IN_PROGRESS;

    public Cart(Long id, User user, List<ProductAndQuantity> productAndQuantityList) {
        this.id = id;
        this.user = user;
        this.productAndQuantityList = productAndQuantityList;
    }

    public Cart(User user, List<ProductAndQuantity> productAndQuantityList) {
        this.user = user;
        this.productAndQuantityList = productAndQuantityList;
    }

    @Override
    public int compareTo(Cart o) {
        return Double.compare(this.getComputedTotal(), o.getComputedTotal());
    }

}

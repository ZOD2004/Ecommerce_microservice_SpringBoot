package com.getgoods.cartservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private Long userId;

    private Date createdAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<CartItem> cartItems;

    public Cart(Long userId, Date createdAt, List<CartItem> cartItems) {
        this.userId = userId;
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
    }
}

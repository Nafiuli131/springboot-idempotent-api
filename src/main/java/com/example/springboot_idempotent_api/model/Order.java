package com.example.springboot_idempotent_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private int quantity;
    private double price;
    private String idempotencyKey;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Order(String product, int quantity, double price, String idempotencyKey, Customer customer) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.idempotencyKey = idempotencyKey;
        this.customer = customer;
    }
}

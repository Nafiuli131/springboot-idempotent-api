package com.example.springboot_idempotent_api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String product;
    private int quantity;
    private double price;
    private String idempotencyKey;
    private Long customerId;
    private String customerName;
    private String customerEmail;
}

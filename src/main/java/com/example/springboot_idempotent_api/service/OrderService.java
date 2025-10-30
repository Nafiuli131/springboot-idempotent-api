package com.example.springboot_idempotent_api.service;

import com.example.springboot_idempotent_api.model.Customer;
import com.example.springboot_idempotent_api.model.Order;
import com.example.springboot_idempotent_api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(String product, int quantity, double price, String idempotencyKey, Customer customer) {
        // Check duplicate request
        Optional<Order> existing = orderRepository.findByIdempotencyKey(idempotencyKey);
        if (existing.isPresent()) {
            System.out.println("Duplicate order detected. Returning existing order.");
            return existing.get();
        }

        Order newOrder = new Order(product, quantity, price, idempotencyKey, customer);
        return orderRepository.save(newOrder);
    }
}

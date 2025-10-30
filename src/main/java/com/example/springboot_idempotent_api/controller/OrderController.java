package com.example.springboot_idempotent_api.controller;

import com.example.springboot_idempotent_api.dto.OrderResponse;
import com.example.springboot_idempotent_api.model.Customer;
import com.example.springboot_idempotent_api.model.Order;
import com.example.springboot_idempotent_api.service.CustomerService;
import com.example.springboot_idempotent_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestParam Long customerId,
            @RequestBody Order request
    ) {
        Customer customer = customerService.findById(customerId);
        Order order = orderService.createOrder(
                request.getProduct(),
                request.getQuantity(),
                request.getPrice(),
                idempotencyKey,
                customer
        );
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setIdempotencyKey(idempotencyKey);
        orderResponse.setPrice(order.getPrice());
        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setProduct(order.getProduct());
        orderResponse.setCustomerId(customer.getId());
        orderResponse.setCustomerName(customer.getName());
        orderResponse.setCustomerEmail(customer.getEmail());
        return ResponseEntity.ok(orderResponse);
    }
}

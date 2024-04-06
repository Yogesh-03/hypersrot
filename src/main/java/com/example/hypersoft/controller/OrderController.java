package com.example.hypersoft.controller;

import com.example.hypersoft.entity.*;
import com.example.hypersoft.error.OrderException;
import com.example.hypersoft.responsemodel.OrderDetailResponse;
import com.example.hypersoft.responsemodel.OrderRequest;
import com.example.hypersoft.responsemodel.OrderResponse;
import com.example.hypersoft.responsemodel.UserOrderResponse;
import com.example.hypersoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}/order")
    public ResponseEntity<Object> placeOrder(@PathVariable Long userId, @RequestParam int qty, @RequestParam String coupon) throws OrderException {
        OrderRequest request = new OrderRequest();
        request.setQuantity(qty);
        request.setCoupon(coupon);

        try {
            OrderResponse response = orderService.placeOrder(userId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("description", e.getMessage()));
        }
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<UserOrderResponse>> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        List<UserOrderResponse> orderResponses;

        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } else {
            return ResponseEntity.ok(
                    orders.stream().map(order -> {
                        UserOrderResponse response = new UserOrderResponse();
                        response.setOrderId(order.getOrderId());
                        response.setAmount(order.getAmount());
                        response.setDate(String.valueOf(order.getDate()));
                        response.setCoupon(order.getCoupon());
                        return response;
                    }).collect(Collectors.toList()));
        }
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetails(@PathVariable Long userId, @PathVariable Long orderId) {
        try {
            List<OrderDetailResponse> response = orderService.getOrderDetails(userId, orderId);
            return ResponseEntity.ok(response);
        } catch (OrderException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.hypersoft.service;

import com.example.hypersoft.entity.*;
import com.example.hypersoft.error.OrderException;
import com.example.hypersoft.responsemodel.OrderDetailResponse;
import com.example.hypersoft.responsemodel.OrderRequest;
import com.example.hypersoft.responsemodel.OrderResponse;

import java.util.List;

public interface OrderService {
    public OrderResponse placeOrder(Long userId, OrderRequest request) throws OrderException;
    //public List<Order> getUserOrders();
    public List<Order> getUserOrders(Long userId);

    public List<OrderDetailResponse> getOrderDetails(Long userId, Long orderId) throws OrderException;
}

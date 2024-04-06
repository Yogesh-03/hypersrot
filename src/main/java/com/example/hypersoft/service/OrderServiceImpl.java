package com.example.hypersoft.service;

import com.example.hypersoft.entity.*;
import com.example.hypersoft.error.OrderException;
import com.example.hypersoft.repository.CouponRepository;
import com.example.hypersoft.repository.OrderRepository;
import com.example.hypersoft.repository.TransactionRepository;
import com.example.hypersoft.repository.UserRepository;
import com.example.hypersoft.responsemodel.OrderDetailResponse;
import com.example.hypersoft.responsemodel.OrderRequest;
import com.example.hypersoft.responsemodel.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InventoryService inventoryService;

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public OrderResponse placeOrder(Long userId, OrderRequest request) throws OrderException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getQuantity() < 1 || request.getQuantity() > 100) {
            throw new OrderException("Invalid quantity");
        }

        Coupon coupon = couponRepository.findCouponByCouponCode(request.getCoupon().trim());
        Optional<Coupon> c = couponRepository.findById(request.getCoupon().trim()) ;
        System.out.println("COUPON: "+  request.getCoupon());
        System.out.println("Coupon found: " + c.isPresent());

        if (coupon == null) {
            throw new OrderException("Invalid coupon");
        }

        int price = 1000; // Assume price of 10 quantity is 1000
        int discount = (int) (price * coupon.getDiscountPercentage() / 100.0);
        int amount = price * request.getQuantity() - discount;

        Order response = new Order();
        //response.setOrderId(); // Generate orderId as per your logic
        response.setUserId(userId);
        response.setQuantity(request.getQuantity());
        response.setAmount(amount);
        response.setCoupon(request.getCoupon());

        Date currentDate = new Date();
        response.setStatus("Ordered");

        response.setDate(currentDate);

        orderRepository.save(response);
        inventoryService.updateOrderedItems(request.getQuantity());

        return new OrderResponse(response.getOrderId(), userId, request.getQuantity(), amount, request.getCoupon());
    }

    public List<OrderDetailResponse> getOrderDetails(Long userId, Long orderId) throws OrderException {
        Order order = orderRepository.findByUserIdAndOrderId(userId, orderId)
                .orElseThrow(() -> new OrderException("Order not found"));

        List<Transaction> transaction = transactionRepository.findByUserIdAndOrderId(userId, orderId);
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();

        for (Transaction value : transaction) {
            OrderDetailResponse response = new OrderDetailResponse();
            response.setOrderId(order.getOrderId());
            response.setAmount(order.getAmount());
            response.setDate(String.valueOf(order.getDate()));
            response.setCoupon(order.getCoupon());
            response.setTransactionId(value.getTransactionId());
            response.setStatus(value.getStatus());
            orderDetailResponses.add(response);
        }

        return orderDetailResponses;
    }
}

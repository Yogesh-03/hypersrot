package com.example.hypersoft.repository;

import com.example.hypersoft.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM order_table WHERE user_id = ?1", nativeQuery = true)
    public List<Order> getUserOrders(Long userId);
    List<Order> findByUserId(Long userId);
    Optional<Order> findByUserIdAndOrderId(Long userId, Long orderId);

}

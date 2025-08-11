package com.getgoods.orderservice.repository;

import com.getgoods.orderservice.entity.Order;
import com.getgoods.orderservice.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<List<Order>> findAllByUserId(Long userId);
    public Optional<Order> findByOrderId(Long orderId);
    public Optional<List<Order>> findOrderByStatus(OrderStatus orderStatus);
    public List<Order> findByOrderDateBetween(Date startDate, Date endDate);
    public Optional<Order> findFirstByUserIdOrderByOrderDateDesc(Long userId);
}

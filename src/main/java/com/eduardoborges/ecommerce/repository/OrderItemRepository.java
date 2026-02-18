package com.eduardoborges.ecommerce.repository;

import com.eduardoborges.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

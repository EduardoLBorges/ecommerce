package com.eduardoborges.ecommerce.repository;

import com.eduardoborges.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

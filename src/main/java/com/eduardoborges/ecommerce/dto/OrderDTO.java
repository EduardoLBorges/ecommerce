package com.eduardoborges.ecommerce.dto;

import com.eduardoborges.ecommerce.entity.Order;
import com.eduardoborges.ecommerce.entity.enums.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderDTO(Long id,
                       Long clientId,
                       List<OrderItemDTO> items,
                       Instant orderDate,
                       Status status,
                       BigDecimal totalAmount) {
    public OrderDTO(Order order){
        this(
                order.getId(),
                order.getClientId().getId(),
                order.getItems().stream().map(OrderItemDTO::new).toList(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount()
        );
    }
}

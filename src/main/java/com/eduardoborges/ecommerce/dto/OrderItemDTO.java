package com.eduardoborges.ecommerce.dto;

import com.eduardoborges.ecommerce.entity.OrderItem;

import java.math.BigDecimal;

public record OrderItemDTO(Long id,
                           Long productId,
                           Long orderId,
                           Integer quantity,
                           BigDecimal unitPrice) {
    public OrderItemDTO(OrderItem orderItem){
        this(
                orderItem.getId(),
                orderItem.getProductId().getId(),
                orderItem.getOrderId().getId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice()
        );
    }
}

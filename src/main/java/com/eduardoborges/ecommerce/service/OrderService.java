package com.eduardoborges.ecommerce.service;

import com.eduardoborges.ecommerce.dto.OrderDTO;
import com.eduardoborges.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<OrderDTO> getOrderById(Long orderId){
        return orderRepository.findById(orderId).
                map(OrderDTO::new);
    }

    public List<OrderDTO> getOrderList(){
        return orderRepository.findAll().
                stream().
                map(OrderDTO::new).
                toList();
    }
}
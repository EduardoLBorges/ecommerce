package com.eduardoborges.ecommerce.controller;

import com.eduardoborges.ecommerce.dto.OrderDTO;
import com.eduardoborges.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderItem")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO){


        return null;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<OrderDTO>> getOrderById(@PathVariable Long orderId){

        var order = orderService.getOrderById(orderId);

        if (order.isPresent())
            return ResponseEntity.ok(order);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public List<OrderDTO> getOrderList(){

        return orderService.getOrderList();
    }
}

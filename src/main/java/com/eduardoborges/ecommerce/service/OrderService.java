package com.eduardoborges.ecommerce.service;

import com.eduardoborges.ecommerce.dto.OrderDTO;
import com.eduardoborges.ecommerce.dto.OrderItemDTO;
import com.eduardoborges.ecommerce.entity.Order;
import com.eduardoborges.ecommerce.entity.OrderItem;
import com.eduardoborges.ecommerce.entity.Product;
import com.eduardoborges.ecommerce.entity.User;
import com.eduardoborges.ecommerce.entity.enums.Status;
import com.eduardoborges.ecommerce.repository.OrderItemRepository;
import com.eduardoborges.ecommerce.repository.OrderRepository;
import com.eduardoborges.ecommerce.repository.ProductRepository;
import com.eduardoborges.ecommerce.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<Long> createOrder(OrderDTO orderDTO){

        var client = userRepository.findById(orderDTO.clientId());

        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var order = new Order();

        order.setClientId(client.get());

        for(OrderItemDTO currentItem:orderDTO.items()) {
            Product product = productRepository.findById(currentItem.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            var orderItem = new OrderItem();
            orderItem.setOrderId(order);
            orderItem.setProductId(product);

            orderItem.setQuantity(currentItem.quantity());
            orderItem.setUnitPrice(product.getPrice());

            order.getItems().add(orderItem);
        };

        order.setOrderDate(Instant.now());
        order.setStatus(Status.PENDING);

        BigDecimal total = BigDecimal.ZERO;
        for(OrderItem currentItem:order.getItems()){
            BigDecimal quantity = BigDecimal.valueOf(currentItem.getQuantity());
            BigDecimal price = currentItem.getUnitPrice();
            total = total.add(price.multiply(quantity));
        };

        order.setTotalAmount(total);

        orderRepository.save(order);

        return ResponseEntity.ok(order.getId());
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
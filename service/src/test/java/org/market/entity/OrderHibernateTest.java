package org.market.entity;

import org.junit.jupiter.api.Test;
import org.market.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderHibernateTest extends GeneralHibernateTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder() {
        Order newOrder = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .totalAmount(new BigDecimal("100.00"))
                .shippingAddress("456 Elm St")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Order savedOrder = orderRepository.save(newOrder);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
    }

    @Test
    void readOrder() {
        Optional<Order> foundOrder = orderRepository.findById(order.getId());

        assertThat(foundOrder.get()).isEqualTo(order);
    }

    @Test
    void updateOrder() {
        Optional<Order> foundOrder = orderRepository.findById(order.getId());
        foundOrder.get().setStatus("COMPLETED");

        Optional<Order> updatedOrder = orderRepository.findById(foundOrder.get().getId());

        assertThat(updatedOrder.get().getStatus()).isEqualTo("COMPLETED");
    }

    @Test
    void deleteOrder() {
        Optional<Order> foundOrder = orderRepository.findById(order.getId());

        orderRepository.delete(foundOrder.orElse(null));

        Optional<Order> deletedOrder = orderRepository.findById(order.getId());
        assertThat(deletedOrder).isEmpty();
    }
}
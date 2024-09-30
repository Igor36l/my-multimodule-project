package org.example.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private Order order;
    private User user;

    @BeforeEach
    public void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .firstName("Alex")
                .lastName("Ivanov")
                .phone("1234567890")
                .address("Moscow 5st street")
                .role(User.Role.USER)
                .gender(User.Gender.MALE)
                .isSeller(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        session.persist(user);

        order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .totalAmount(new BigDecimal("100.00"))
                .shippingAddress("456 Elm St")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @AfterEach
    public void tearDown() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testCreateOrder() {
        session.persist(order);
        transaction.commit();
        session.beginTransaction();

        Order savedOrder = session.get(Order.class, order.getId());
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
    }

    @Test
    public void testReadOrder() {
        session.persist(order);
        transaction.commit();
        session.beginTransaction();

        Order foundOrder = session.get(Order.class, order.getId());
        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder).isEqualTo(order);
    }

    @Test
    public void testUpdateOrder() {
        session.persist(order);
        transaction.commit();
        session.beginTransaction();

        Order foundOrder = session.get(Order.class, order.getId());
        foundOrder.setStatus("COMPLETED");
        session.merge(foundOrder);
        transaction.commit();
        session.beginTransaction();

        Order updatedOrder = session.get(Order.class, order.getId());
        assertThat(updatedOrder.getStatus()).isEqualTo("COMPLETED");
    }

    @Test
    public void testDeleteOrder() {
        session.persist(order);
        transaction.commit();
        session.beginTransaction();

        session.remove(order);
        transaction.commit();
        session.beginTransaction();

        Order deletedOrder = session.get(Order.class, order.getId());
        assertThat(deletedOrder).isNull();
    }
}
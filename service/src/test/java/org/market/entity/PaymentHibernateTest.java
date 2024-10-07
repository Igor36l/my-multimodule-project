package org.market.entity;

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

public class PaymentHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private Order order;
    private Payment payment;
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

        session.persist(order);

        payment = Payment.builder()
                .amount(BigDecimal.valueOf(1000))
                .order(order)
                .paymentDate(LocalDateTime.now())
                .paymentMethod("Card")
                .status(Payment.Status.IN_PROGRESS)
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
    public void testCreatePayment() {
        session.persist(payment);
        transaction.commit();
        session.beginTransaction();

        Payment savedPayment = session.get(Payment.class, payment.getId());
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isNotNull();
    }

    @Test
    public void testReadPayment() {
        session.persist(payment);
        transaction.commit();
        session.beginTransaction();

        Payment savedPayment = session.get(Payment.class, payment.getId());
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isNotNull();
    }

    @Test
    public void testUpdatePayment() {
        session.persist(payment);
        transaction.commit();
        session.beginTransaction();

        Payment foundPayment = session.get(Payment.class, payment.getId());
        foundPayment.setStatus(Payment.Status.DONE);
        session.merge(foundPayment);
        transaction.commit();
        session.beginTransaction();

        Payment updatedPayment = session.get(Payment.class, payment.getId());
        assertThat(updatedPayment.getStatus()).isEqualByComparingTo(Payment.Status.DONE);
    }

    @Test
    public void testDeletePayment() {
        session.persist(payment);
        transaction.commit();
        session.beginTransaction();

        session.remove(payment);
        transaction.commit();
        session.beginTransaction();

        Payment deletedPayment = session.get(Payment.class, payment.getId());
        assertThat(deletedPayment).isNull();
    }

}

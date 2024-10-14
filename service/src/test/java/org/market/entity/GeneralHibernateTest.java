package org.market.entity;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.market.util.HibernateTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GeneralHibernateTest {

    protected User user;
    protected Category category;
    protected Category parentCategory;
    protected Transaction transaction;
    protected Order order;
    protected Payment payment;
    protected Review review;
    protected Session session;

    protected static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp() {
        sessionFactory = HibernateTestUtils.buildSessionFactory();
    }

    @BeforeEach
       void beforeEach() {
        session = sessionFactory.getCurrentSession();
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

        parentCategory = Category.builder()
                .name("Parent Category")
                .description("This is a parent category")
                .build();
        session.persist(parentCategory);

        category = Category.builder()
                .name("Child Category")
                .description("This is a child category")
                .parentCategory(parentCategory)
                .build();
        session.persist(category);

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
        session.persist(payment);

        review = Review.builder()
                .comment("Comment")
                .rating(5)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        session.persist(review);
    }

    @AfterEach
    void afterEach() {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }
}

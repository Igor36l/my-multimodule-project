package org.market.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.market.MyApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@SpringBootTest(classes = MyApplication.class)
@TestConfiguration
public class GeneralHibernateTest {

    protected User user;
    protected Category category;
    protected Category parentCategory;
    protected Order order;
    protected Payment payment;
    protected Review review;

    protected static EntityManager entityManager;
    protected static ApplicationContext context;

    protected final static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @BeforeEach
       void beforeEachGeneral() {
        entityManager.getTransaction().begin();
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
        entityManager.persist(user);

        parentCategory = Category.builder()
                .name("Parent Category")
                .description("This is a parent category")
                .build();
        entityManager.persist(parentCategory);

        category = Category.builder()
                .name("Child Category")
                .description("This is a child category")
                .parentCategory(parentCategory)
                .build();
        entityManager.persist(category);

        order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .totalAmount(new BigDecimal("100.00"))
                .shippingAddress("456 Elm St")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        entityManager.persist(order);

        payment = Payment.builder()
                .amount(BigDecimal.valueOf(1000))
                .order(order)
                .paymentDate(LocalDateTime.now())
                .paymentMethod("Card")
                .status(Payment.Status.IN_PROGRESS)
                .build();
        entityManager.persist(payment);

        review = Review.builder()
                .comment("Comment")
                .rating(5)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        entityManager.persist(review);
    }

    @AfterEach
    void afterEachGeneral() {
        if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
}

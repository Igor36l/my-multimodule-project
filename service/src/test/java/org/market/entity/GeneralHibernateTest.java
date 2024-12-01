package org.market.entity;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.market.StoreApplicationRunner;
import org.market.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@SpringBootTest(classes = StoreApplicationRunner.class)
@Transactional
public class GeneralHibernateTest {

    protected User user;
    protected Category category;
    protected Category parentCategory;
    protected Order order;
    protected Payment payment;
    protected Review review;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReviewRepository reviewRepository;



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


    @BeforeEach
    void beforeEachGeneral() {
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
        userRepository.save(user);

        parentCategory = Category.builder()
                .name("Parent Category")
                .description("This is a parent category")
                .build();
        categoryRepository.save(parentCategory);

        category = Category.builder()
                .name("Child Category")
                .description("This is a child category")
                .parentCategory(parentCategory)
                .build();
        categoryRepository.save(category);

        order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .totalAmount(new BigDecimal("100.00"))
                .shippingAddress("456 Elm St")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        orderRepository.save(order);

        payment = Payment.builder()
                .amount(BigDecimal.valueOf(1000))
                .order(order)
                .paymentDate(LocalDateTime.now())
                .paymentMethod("Card")
                .status(Payment.Status.IN_PROGRESS)
                .build();
        paymentRepository.save(payment);

        review = Review.builder()
                .comment("Comment")
                .rating(5)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        reviewRepository.save(review);
    }

}

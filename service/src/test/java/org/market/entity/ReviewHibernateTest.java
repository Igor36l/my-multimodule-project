package org.market.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private User user;
    private Review review;

    @BeforeEach
    void setUp(){
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
    public void tearDown() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testCreateReview() {
        session.persist(review);
        transaction.commit();
        session.beginTransaction();

        Review savedReview = session.get(Review.class, review.getId());
        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
    public void testReadReview() {
        session.persist(review);
        transaction.commit();
        session.beginTransaction();

        Review savedReview = session.get(Review.class, review.getId());
        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
    public void testUpdateReview() {
        session.persist(review);
        transaction.commit();
        session.beginTransaction();

        Review foundReview = session.get(Review.class, review.getId());
        foundReview.setComment("New comment");
        session.merge(foundReview);
        transaction.commit();
        session.beginTransaction();

        Review updatedPayment = session.get(Review.class, review.getId());
        assertThat(updatedPayment.getComment().equals("New comment"));
    }

    @Test
    public void testDeletePayment() {
        session.persist(review);
        transaction.commit();
        session.beginTransaction();

        session.remove(review);
        transaction.commit();
        session.beginTransaction();

        Review deletedReview = session.get(Review.class, review.getId());
        assertThat(deletedReview).isNull();
    }



}
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

public class UserHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
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
    public void testCreateUser() {
        session.persist(user);
        transaction.commit();
        session.beginTransaction();

        User savedUser = session.get(User.class, user.getId());
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void testReadUser() {
        session.persist(user);
        transaction.commit();
        session.beginTransaction();

        User foundUser = session.get(User.class, user.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    public void testUpdateUser() {
        session.persist(user);
        transaction.commit();
        session.beginTransaction();

        User foundUser = session.get(User.class, user.getId());
        foundUser.setUsername("updateduser");
        session.merge(foundUser);
        transaction.commit();
        session.beginTransaction();

        User updatedUser = session.get(User.class, user.getId());
        assertThat(updatedUser.getUsername()).isEqualTo("updateduser");
    }

    @Test
    public void testDeleteUser() {
        session.persist(user);
        transaction.commit();
        session.beginTransaction();

        session.remove(user);
        transaction.commit();
        session.beginTransaction();

        User deletedUser = session.get(User.class, user.getId());
        assertThat(deletedUser).isNull();
    }

}
package org.market;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.market.entity.User;
import org.market.util.HibernateUtils;

import java.time.LocalDateTime;

public class HibernateRunner {
    public static void main(String[] args) {
        @Cleanup SessionFactory sessionFactory = HibernateUtils.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = User.builder()
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
        session.persist(user);
        session.getTransaction().commit();
    }
}

package org.market;

import jakarta.persistence.EntityManager;
import org.market.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

@ComponentScan
public class HibernateRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateRunner.class);
        EntityManager entityManager = context.getBean(EntityManager.class);
        entityManager .getTransaction().begin();
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
        entityManager .persist(user);
        entityManager .persist(user);
        entityManager .getTransaction().commit();
    }
}

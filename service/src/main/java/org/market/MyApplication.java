package org.market;

import jakarta.persistence.EntityManager;
import org.market.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
            SpringApplication.run(MyApplication.class, args);

    }
}

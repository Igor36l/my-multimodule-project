package org.market.configuration;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.market.HibernateRunner;
import org.market.util.HibernateTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = HibernateRunner.class)
public class MainTestConfig {

    @Bean
    public EntityManager getEntityManager() {
        SessionFactory sessionFactory = HibernateTestUtils.buildSessionFactory();
        return sessionFactory.openSession();
    }
}

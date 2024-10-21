package org.market.configuration;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.market.App;
import org.market.util.HibernateTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = App.class)
public class MainTestConfig {

    @Bean
    public EntityManager getEntityManager() {
        SessionFactory sessionFactory = HibernateTestUtils.buildSessionFactory();
        return sessionFactory.openSession();
    }
}

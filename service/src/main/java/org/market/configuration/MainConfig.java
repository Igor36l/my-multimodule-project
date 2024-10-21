package org.market.configuration;

import jakarta.persistence.EntityManager;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.market.util.HibernateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainConfig {

    @Bean
    public EntityManager getEntityManager() {
        @Cleanup SessionFactory sessionFactory = HibernateUtils.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}

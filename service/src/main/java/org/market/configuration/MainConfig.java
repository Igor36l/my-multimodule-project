package org.market.configuration;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.market.util.HibernateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.market")
public class MainConfig {

    @Bean
    public EntityManager getEntityManager() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}

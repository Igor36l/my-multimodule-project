package org.market.configoration;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.market.repository.CategoryRepository;
import org.market.util.HibernateTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainConfig {

    @Bean
    public EntityManager getEntityManager() {
        SessionFactory sessionFactory = HibernateTestUtils.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    @Bean
    public CategoryRepository getCategoryRepository() {
        return new CategoryRepository(getEntityManager());
    }
}

package org.market.repository;

import jakarta.persistence.EntityManager;
import org.market.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends RepositoryBase<Long, Order> {

    public OrderRepository(EntityManager entityManager) {
        super(Order.class, entityManager);
    }

}

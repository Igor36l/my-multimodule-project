package org.market.dao;

import jakarta.persistence.EntityManager;
import org.market.entity.Order;

public class OrderRepository extends RepositoryBase<Long, Order> {

    public OrderRepository(EntityManager entityManager) {
        super(Order.class, entityManager);
    }

}

package org.market.dao;

import jakarta.persistence.EntityManager;
import org.market.entity.Payment;

public class PaymentRepository extends RepositoryBase<Long, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);

    }

}

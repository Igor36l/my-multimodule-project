package org.market.repository;

import jakarta.persistence.EntityManager;
import org.market.entity.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository extends RepositoryBase<Long, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);

    }

}

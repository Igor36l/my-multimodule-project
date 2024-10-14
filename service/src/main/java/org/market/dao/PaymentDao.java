package org.market.dao;

import org.hibernate.SessionFactory;
import org.market.entity.Payment;

public class PaymentDao extends DaoBase<Long, Payment> {

    public PaymentDao(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);

    }

}

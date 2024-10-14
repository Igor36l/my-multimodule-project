package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentHibernateTest extends GeneralHibernateTest {

    @Test
    public void testCreatePayment() {
        Payment savedPayment = session.get(Payment.class, payment.getId());

        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isNotNull();
    }

    @Test
    public void testReadPayment() {
        Payment savedPayment = session.get(Payment.class, payment.getId());

        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isNotNull();
    }

    @Test
    public void testUpdatePayment() {
        Payment foundPayment = session.get(Payment.class, payment.getId());
        foundPayment.setStatus(Payment.Status.DONE);

        Payment updatedPayment = session.get(Payment.class, payment.getId());

        assertThat(updatedPayment.getStatus()).isEqualByComparingTo(Payment.Status.DONE);
    }

    @Test
    public void testDeletePayment() {
        session.remove(payment);

        Payment deletedPayment = session.get(Payment.class, payment.getId());

        assertThat(deletedPayment).isNull();
    }

}

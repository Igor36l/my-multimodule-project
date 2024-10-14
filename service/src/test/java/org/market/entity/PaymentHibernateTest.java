package org.market.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.market.dao.CategoryRepository;
import org.market.dao.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentHibernateTest extends GeneralHibernateTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        paymentRepository = new PaymentRepository(session);
    }

    @Test
    void createPayment() {
        Payment newPayment = Payment.builder()
                .amount(BigDecimal.valueOf(1000))
                .order(order)
                .paymentDate(LocalDateTime.now())
                .paymentMethod("Card")
                .status(Payment.Status.IN_PROGRESS)
                .build();
        Payment savedPayment = paymentRepository.save(newPayment);

        assertThat(savedPayment.getId()).isNotNull();
    }

    @Test
    public void readPayment() {
        Optional<Payment> foundPayment = paymentRepository.findById(payment.getId());

        assertThat(foundPayment.get()).isNotNull();
        assertThat(foundPayment.get().getId()).isNotNull();
    }

    @Test
    void updatePayment() {
        Optional<Payment> foundPayment = paymentRepository.findById(payment.getId());
        foundPayment.get().setStatus(Payment.Status.DONE);

        Payment updatedPayment = paymentRepository.update(foundPayment.get());

        assertThat(updatedPayment.getStatus()).isEqualByComparingTo(Payment.Status.DONE);
    }

    @Test
    void deletePayment() {
        paymentRepository.delete(payment.getId());

        Payment deletedPayment = session.get(Payment.class, payment.getId());

        assertThat(deletedPayment).isNull();
    }

}

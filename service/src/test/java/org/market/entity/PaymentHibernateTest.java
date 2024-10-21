package org.market.entity;

import org.junit.jupiter.api.Test;
import org.market.repository.OrderRepository;
import org.market.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentHibernateTest extends GeneralHibernateTest {

    private final PaymentRepository paymentRepository = context.getBean(PaymentRepository.class);
    private final OrderRepository orderRepository = context.getBean(OrderRepository.class);

    @Test
    void createPayment() {
        Order newOrder = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .totalAmount(new BigDecimal("100.00"))
                .shippingAddress("456 Elm St")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Payment newPayment = Payment.builder()
                .amount(BigDecimal.valueOf(1000))
                .order(newOrder)
                .paymentDate(LocalDateTime.now())
                .paymentMethod("Card")
                .status(Payment.Status.IN_PROGRESS)
                .build();

        orderRepository.save(newOrder);
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
        Optional<Payment> foundPayment = paymentRepository.findById(payment.getId());

        paymentRepository.delete(foundPayment.orElse(null));

        Payment deletedPayment = entityManager.find(Payment.class, payment.getId());
        assertThat(deletedPayment).isNull();
    }

}

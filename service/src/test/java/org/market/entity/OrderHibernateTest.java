package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderHibernateTest extends GeneralHibernateTest {

    @Test
    public void testCreateOrder() {
        Order savedOrder = session.get(Order.class, order.getId());

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
    }

    @Test
    public void testReadOrder() {
        Order foundOrder = session.get(Order.class, order.getId());

        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder).isEqualTo(order);
    }

    @Test
    public void testUpdateOrder() {
        Order foundOrder = session.get(Order.class, order.getId());
        foundOrder.setStatus("COMPLETED");

        Order updatedOrder = session.get(Order.class, order.getId());

        assertThat(updatedOrder.getStatus()).isEqualTo("COMPLETED");
    }

    @Test
    public void testDeleteOrder() {
        session.remove(order);

        Order deletedOrder = session.get(Order.class, order.getId());

        assertThat(deletedOrder).isNull();
    }
}
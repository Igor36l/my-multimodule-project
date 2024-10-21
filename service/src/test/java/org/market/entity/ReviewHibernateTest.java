package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewHibernateTest extends GeneralHibernateTest {

    @Test
    void testCreateReview() {
        Review savedReview = entityManager.find(Review.class, review.getId());

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
   void testReadReview() {
        Review savedReview = entityManager.find(Review.class, review.getId());

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
   void testUpdateReview() {
        Review foundReview = entityManager.find(Review.class, review.getId());
        foundReview.setComment("New comment");

        Review updatedPayment = entityManager.find(Review.class, review.getId());

        assertThat(updatedPayment.getComment().equals("New comment"));
    }

    @Test
    void testDeletePayment() {
        entityManager.remove(review);

        Review deletedReview = entityManager.find(Review.class, review.getId());

        assertThat(deletedReview).isNull();
    }

}

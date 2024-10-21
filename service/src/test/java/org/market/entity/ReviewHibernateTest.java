package org.market.entity;

import org.junit.jupiter.api.Test;
import org.market.repository.OrderRepository;
import org.market.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewHibernateTest extends GeneralHibernateTest {

    private final ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);

    @Test
    void testCreateReview() {
        //given
        Review newReview = Review.builder()
                .comment("Comment")
                .rating(5)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        //when
        Review savedReview = reviewRepository.save(newReview);

        //then
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
   void testReadReview() {
        //when
        Optional<Review> foundReview = reviewRepository.findById(review.getId());

        //then
        assertThat(foundReview.get().getId()).isNotNull();
    }

    @Test
   void testUpdateReview() {
        //given
        Optional<Review> foundReview = reviewRepository.findById(review.getId());
        foundReview.get().setComment("New comment");

        //when
        Optional<Review> updatedPayment = reviewRepository.findById(review.getId());

        //then
        assertThat(updatedPayment.get().getComment().equals("New comment"));
    }

    @Test
    void testDeletePayment() {
        //given
        Optional<Review> foundReview = reviewRepository.findById(review.getId());

        //when
        reviewRepository.delete(foundReview.orElse(null));

        //then
        Optional<Review> deletedReview = reviewRepository.findById(review.getId());
        assertThat(deletedReview).isEmpty();
    }

}

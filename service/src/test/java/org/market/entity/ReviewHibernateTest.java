package org.market.entity;

import org.junit.jupiter.api.Test;
import org.market.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewHibernateTest extends GeneralHibernateTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void createReview() {
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
    void readReview() {
        //when
        Optional<Review> foundReview = reviewRepository.findById(review.getId());

        //then
        assertThat(foundReview.isPresent()).isTrue();
    }

    @Test
    void testUpdateReview() {
        //given
        Optional<Review> foundReview = reviewRepository.findById(review.getId());
        foundReview.ifPresent(review -> review.setComment("New comment"));

        //when
        Optional<Review> updatedPayment = reviewRepository.findById(review.getId());

        //then
        assertThat(updatedPayment.get().getComment().equals("New comment"));
    }

    @Test
    void deletePayment() {
        //given
        Optional<Review> foundReview = reviewRepository.findById(review.getId());

        //when
        reviewRepository.delete(foundReview.orElse(null));

        //then
        Optional<Review> deletedReview = reviewRepository.findById(review.getId());
        assertThat(deletedReview).isEmpty();
    }

}

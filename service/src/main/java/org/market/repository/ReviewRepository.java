package org.market.repository;

import jakarta.persistence.EntityManager;
import org.market.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository extends RepositoryBase<Long, Review> {

    public ReviewRepository(EntityManager entityManager) {
        super(Review.class, entityManager);

    }

}

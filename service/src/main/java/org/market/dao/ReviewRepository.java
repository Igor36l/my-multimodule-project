package org.market.dao;

import jakarta.persistence.EntityManager;
import org.market.entity.Review;

public class ReviewRepository extends RepositoryBase<Long, Review> {

    public ReviewRepository(EntityManager entityManager) {
        super(Review.class, entityManager);

    }

}

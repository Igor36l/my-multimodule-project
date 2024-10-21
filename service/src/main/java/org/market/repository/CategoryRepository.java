package org.market.repository;

import jakarta.persistence.EntityManager;
import org.market.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends RepositoryBase<Long, Category> {

    public CategoryRepository(EntityManager entityManager) {
        super(Category.class, entityManager);
    }

}

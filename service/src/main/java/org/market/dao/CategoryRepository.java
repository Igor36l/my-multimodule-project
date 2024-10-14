package org.market.dao;

import jakarta.persistence.EntityManager;
import org.market.entity.Category;

public class CategoryRepository extends RepositoryBase<Long, Category> {

    public CategoryRepository(EntityManager entityManager) {
        super(Category.class, entityManager);
    }

}

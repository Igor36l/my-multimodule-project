package org.market.dao;

import jakarta.persistence.EntityManager;
import org.market.entity.User;

public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);

    }

}

package org.market.repository;

import jakarta.persistence.EntityManager;
import org.market.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);

    }

}

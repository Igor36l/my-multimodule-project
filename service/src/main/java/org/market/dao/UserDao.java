package org.market.dao;

import org.hibernate.SessionFactory;
import org.market.entity.User;

public class UserDao extends DaoBase<Long, User> {

    public UserDao(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);

    }

}

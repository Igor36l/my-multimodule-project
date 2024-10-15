package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserHibernateTest extends GeneralHibernateTest{

    @Test
    void createUser() {
        User savedUser = session.get(User.class, user.getId());

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void readUser() {
        User foundedUser = session.get(User.class, user.getId());

        assertThat(foundedUser).isNotNull();
        assertThat(foundedUser).isEqualTo(user);
    }

    @Test
    void updateUser() {
        User foundedUser = session.get(User.class, user.getId());
        foundedUser.setUsername("updateduser");

        User updatedUser = session.get(User.class, user.getId());

        assertThat(updatedUser.getUsername()).isEqualTo("updateduser");
    }

    @Test
    void deleteUser() {
        session.remove(user);

        User deletedUser = session.get(User.class, user.getId());

        assertThat(deletedUser).isNull();
    }

}
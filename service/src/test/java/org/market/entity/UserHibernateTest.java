package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserHibernateTest extends GeneralHibernateTest{

    @Test
    void testCreateUser() {
        User savedUser = session.get(User.class, user.getId());

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void testReadUser() {
        User foundUser = session.get(User.class, user.getId());

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testUpdateUser() {
        User foundUser = session.get(User.class, user.getId());
        foundUser.setUsername("updateduser");

        User updatedUser = session.get(User.class, user.getId());

        assertThat(updatedUser.getUsername()).isEqualTo("updateduser");
    }

    @Test
    void testDeleteUser() {
        session.remove(user);

        User deletedUser = session.get(User.class, user.getId());

        assertThat(deletedUser).isNull();
    }

}
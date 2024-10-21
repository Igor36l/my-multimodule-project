package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserHibernateTest extends GeneralHibernateTest {

    @Test
    void createUser() {
        User savedUser = entityManager.find(User.class, user.getId());

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void readUser() {
        User foundedUser = entityManager.find(User.class, user.getId());

        assertThat(foundedUser).isNotNull();
        assertThat(foundedUser).isEqualTo(user);
    }

    @Test
    void updateUser() {
        User foundedUser = entityManager.find(User.class, user.getId());
        foundedUser.setUsername("updateduser");

        User updatedUser = entityManager.find(User.class, user.getId());

        assertThat(updatedUser.getUsername()).isEqualTo("updateduser");
    }

    @Test
    void deleteUser() {
        entityManager.remove(user);

        User deletedUser = entityManager.find(User.class, user.getId());

        assertThat(deletedUser).isNull();
    }
}

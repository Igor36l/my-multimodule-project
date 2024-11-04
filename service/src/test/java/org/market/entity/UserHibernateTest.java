package org.market.entity;

import org.junit.jupiter.api.Test;
import org.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserHibernateTest extends GeneralHibernateTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        //given
        User newUser = User.builder()
                .username("newtestuser")
                .email("newtestuser@example.com")
                .password("password")
                .firstName("Jason")
                .lastName("Ivanov")
                .phone("1234567890")
                .address("Moscow 5st street")
                .role(User.Role.USER)
                .gender(User.Gender.MALE)
                .isSeller(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        //when
        User savedUser = userRepository.save(newUser);

        //then
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void readUser() {
        //when
        Optional<User> foundUser = userRepository.findById(user.getId());

        //then
        assertThat(foundUser.isPresent()).isTrue();
    }

    @Test
    void updateUser() {
        //given
        Optional<User> foundUser = userRepository.findById(user.getId());
        foundUser.ifPresent(user -> user.setUsername("updateduser"));

        //when
        Optional<User> updatedUser = userRepository.findById(user.getId());

        //then
        assertThat(updatedUser.get().getUsername()).isEqualTo("updateduser");
    }

    @Test
    void deleteUser() {
        //given
        Optional<User> foundUser = userRepository.findById(user.getId());

        //when
        userRepository.delete(foundUser.orElse(null));

        //then
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertThat(deletedUser.isEmpty()).isTrue();
    }
}

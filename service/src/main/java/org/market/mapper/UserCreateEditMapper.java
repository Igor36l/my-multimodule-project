package org.market.mapper;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.UserCreateEditDto;
import org.market.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.username());
        user.setEmail(object.email());
        user.setPassword(passwordEncoder.encode(object.password()));
        user.setGender(parseGender(object.gender()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    private User.Gender parseGender(String gender) {
        if (gender.equals("MALE")) {
            return User.Gender.MALE;
        } else {
            return User.Gender.FEMALE;
        }
    }
}

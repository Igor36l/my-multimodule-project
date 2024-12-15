package org.market.mapper;

import org.market.controller.dto.UserReadDto;
import org.market.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto>{

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(object.getId(), object.getUsername(), object.getEmail(), object.getPassword(), object.getGender().name(), object.getIsSeller());
    }
}

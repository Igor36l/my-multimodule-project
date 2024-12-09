package org.market.service;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.UserCreateEditDto;
import org.market.controller.dto.UserReadDto;
import org.market.entity.User;
import org.market.mapper.UserCreateEditMapper;
import org.market.mapper.UserReadMapper;
import org.market.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @Transactional
    public UserReadDto createUser(UserCreateEditDto userDto) {
        User user = userCreateEditMapper.map(userDto);
        User savedUser = userRepository.save(user);
        return userReadMapper.map(savedUser);
    }

    @Transactional
    public void delete(Long id) {
    }

    @Transactional
    public void update(Long id, UserCreateEditDto userDto) {
        Optional<User> updatedUser = userRepository.findById(id)
                .map(user -> userCreateEditMapper.map(userDto, user))
                .map(userRepository::save);

//        return updatedUser.map(userReadMapper::map);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUserName = userRepository.findUserByUserName(username);
        return userByUserName.orElse(null);

    }

//    public User getUserByUsername(String username) {
//        Optional<User> userByUserName = userRepository.findUserByUserName(username);
//        return userByUserName.orElseThrow();
//    }
}

package org.market.service;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.SellerCreateEditDto;
import org.market.controller.dto.SellerReadDto;
import org.market.entity.Seller;
import org.market.entity.User;
import org.market.mapper.SellerCreateEditMapper;
import org.market.mapper.SellerReadMapper;
import org.market.repository.SellerRepository;
import org.market.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final SellerReadMapper sellerReadMapper;
    private final SellerCreateEditMapper sellerCreateEditMapper;


    public Optional<SellerReadDto> create(Long userId, SellerCreateEditDto dto) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setIsSeller(true);
        userRepository.save(user);
        Seller save = sellerRepository.save(sellerCreateEditMapper.map(dto));
        return Optional.ofNullable(sellerReadMapper.map(save));
    }

    public Optional<SellerReadDto> findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Optional<Seller> byUser = sellerRepository.findByUser(user);
        return byUser.map(sellerReadMapper::map);
    }
}

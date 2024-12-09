package org.market.mapper;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.SellerCreateEditDto;
import org.market.entity.Seller;
import org.market.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SellerCreateEditMapper implements Mapper<SellerCreateEditDto, Seller> {

    private final UserRepository userRepository;

    @Override
    public Seller map(SellerCreateEditDto object) {
        return Seller.builder()
                .user(userRepository.findById(object.userId()).get())
                .organizationName(object.organizationName())
                .organizationAddress(object.organizationAddress())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

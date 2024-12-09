package org.market.mapper;

import org.market.controller.dto.SellerReadDto;
import org.market.entity.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerReadMapper implements Mapper<Seller, SellerReadDto> {

    @Override
    public SellerReadDto map(Seller object) {
        return new SellerReadDto(
                object.getId(),
                object.getOrganizationName()
        );
    }
}

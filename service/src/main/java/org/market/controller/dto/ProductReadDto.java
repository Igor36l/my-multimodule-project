package org.market.controller.dto;

import org.market.entity.Seller;

import java.math.BigDecimal;
import java.util.List;

public record ProductReadDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        List<CategoryReadDto> categories,
        Seller seller
        ) {
}

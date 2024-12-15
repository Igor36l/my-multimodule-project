package org.market.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductCreateEditDto(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        List<Long> categoryIds,
        Long sellerId
) {
}

package org.market.controller.dto;

import java.util.List;

public record BucketReadDto(
        Long id,
        List<ProductReadDto> products,
        Long userId
) {
}

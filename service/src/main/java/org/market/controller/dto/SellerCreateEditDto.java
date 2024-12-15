package org.market.controller.dto;

public record SellerCreateEditDto(
        Long userId,
        String organizationName,
        String organizationAddress
) {
}

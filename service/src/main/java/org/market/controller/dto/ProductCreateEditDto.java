package org.market.controller.dto;

import java.math.BigDecimal;

public record ProductCreateEditDto(String name, String description, BigDecimal price) {
}

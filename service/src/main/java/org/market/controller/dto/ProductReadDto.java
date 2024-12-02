package org.market.controller.dto;

import java.math.BigDecimal;

public record ProductReadDto(Long id, String name, String description, BigDecimal price) {
}

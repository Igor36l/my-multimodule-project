package org.market.repository.filter;

import java.math.BigDecimal;

public record ProductFilter (String name, BigDecimal minPrice, BigDecimal maxPrice){
}

package org.market.mapper;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.ProductReadDto;
import org.market.entity.Product;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {

    private final CategoryReadMapper categoryReadMapper;

    @Override
    public ProductReadDto map(Product object) {
        return new ProductReadDto(
                object.getId(),
                object.getName(),
                object.getDescription(),
                object.getPrice(),
                object.getStock(),
                object.getCategory().stream().map(categoryReadMapper::map).toList(),
                object.getSeller()
        );
    }
}

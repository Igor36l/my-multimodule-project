package org.market.mapper;

import lombok.experimental.UtilityClass;
import org.market.controller.dto.ProductCreateEditDto;
import org.market.entity.Product;

@UtilityClass
public class ProductMapper {

    public static Product toProduct(ProductCreateEditDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .build();
    }

    public static ProductCreateEditDto fromProduct(Product product) {
        return new ProductCreateEditDto(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}

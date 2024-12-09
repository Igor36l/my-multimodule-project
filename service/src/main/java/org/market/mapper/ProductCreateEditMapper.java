package org.market.mapper;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.ProductCreateEditDto;
import org.market.entity.Category;
import org.market.entity.Product;
import org.market.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDto, Product>{

    private final CategoryRepository categoryRepository;

    @Override
    public Product map(ProductCreateEditDto object) {
        return Product.builder()
                .name(object.name())
                .description(object.description())
                .price(object.price())
                .category(findCategoriesById(object.categoryIds()))
                .stock(object.stock())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public Product map(ProductCreateEditDto object, Product to){
        to.setName(object.name());
        to.setDescription(object.description());
        to.setPrice(object.price());
        to.getCategory().clear();
        to.getCategory().addAll(findCategoriesById(object.categoryIds()));
        to.setStock(object.stock());
        to.setUpdatedAt(LocalDateTime.now());
        return to;
    }

    private List<Category> findCategoriesById(List<Long> ids){
        return ids.stream()
                .map(categoryRepository::findById)
                .map(optionalCategory -> optionalCategory.orElseThrow(() -> new IllegalArgumentException("Category not found")))
                .toList();
    }
}

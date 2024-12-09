package org.market.mapper;

import org.market.controller.dto.CategoryCreateEditDto;
import org.market.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryCreateEditMapper implements Mapper<CategoryCreateEditDto, Category> {

    @Override
    public Category map(CategoryCreateEditDto object) {
        return Category.builder()
                .name(object.name())
                .build();
    }
}

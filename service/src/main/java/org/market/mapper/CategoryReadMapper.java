package org.market.mapper;

import org.market.controller.dto.CategoryReadDto;
import org.market.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryReadMapper implements Mapper<Category, CategoryReadDto> {

    @Override
    public CategoryReadDto map(Category object) {
        return new CategoryReadDto(
                object.getId(),
                object.getName()
        );
    }
}

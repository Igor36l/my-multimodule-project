package org.market.service;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.CategoryCreateEditDto;
import org.market.controller.dto.CategoryReadDto;
import org.market.entity.Category;
import org.market.mapper.CategoryCreateEditMapper;
import org.market.mapper.CategoryReadMapper;
import org.market.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCreateEditMapper categoryCreateEditMapper;
    private final CategoryReadMapper categoryReadMapper;

    public List<CategoryReadDto> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream().map(categoryReadMapper::map).toList();

    }

    @Transactional
    public CategoryReadDto createCategory(CategoryCreateEditDto dto) {
        Category save = categoryRepository.save(categoryCreateEditMapper.map(dto));
        return categoryReadMapper.map(save);
    }
}

package org.market.entity;

import org.junit.jupiter.api.Test;
import org.market.dao.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryHibernateTest extends GeneralHibernateTest {


    @Test
    void createCategory() {
        CategoryRepository categoryRepository = new CategoryRepository(session);
        Category newCategory = Category.builder()
                .name("New child Category")
                .description("This is a new child category")
                .parentCategory(parentCategory)
                .build();
        Category savedCategory = categoryRepository.save(newCategory);

        assertThat(savedCategory.getId()).isNotNull();
    }

    @Test
    void readCategory() {
        CategoryRepository categoryRepository = new CategoryRepository(session);
        Optional<Category> foundCategory = categoryRepository.findById(category.getId());

        assertThat(foundCategory.get()).isNotNull();
        assertThat(foundCategory.get()).isEqualTo(category);
    }

    @Test
    void updateCategory() {
        CategoryRepository categoryRepository = new CategoryRepository(session);
        Optional<Category> foundCategory = categoryRepository.findById(category.getId());
        foundCategory.get().setName("Updated Category");

        Optional<Category> updatedCategory = categoryRepository.findById(category.getId());

        assertThat(updatedCategory.get().getName()).isEqualTo("Updated Category");
    }

    @Test
    void deleteCategory() {
        CategoryRepository categoryRepository = new CategoryRepository(session);
        categoryRepository.delete(category.getId());

        Optional<Category> deletedCategory = categoryRepository.findById(category.getId());

        assertThat(deletedCategory).isEmpty();
    }
}
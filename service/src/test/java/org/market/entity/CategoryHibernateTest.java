package org.market.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryHibernateTest extends GeneralHibernateTest {


    @Test
    void testCreateCategory() {
        Category savedCategory = session.get(Category.class, category.getId());

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
    }

    @Test
    void testReadCategory() {
        Category foundCategory = session.get(Category.class, category.getId());

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory).isEqualTo(category);
    }

    @Test
    void updateCategory() {
        Category foundCategory = session.get(Category.class, category.getId());
        foundCategory.setName("Updated Category");

        Category updatedCategory = session.get(Category.class, category.getId());

        assertThat(updatedCategory.getName()).isEqualTo("Updated Category");
    }

    @Test
    void deleteCategory() {
        session.remove(category);

        Category deletedCategory = session.get(Category.class, category.getId());

        assertThat(deletedCategory).isNull();
    }
}
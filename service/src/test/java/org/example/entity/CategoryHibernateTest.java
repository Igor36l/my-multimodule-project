package org.example.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private Category category;
    private Category parentCategory;

    @BeforeEach
    public void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        parentCategory = Category.builder()
                .name("Parent Category")
                .description("This is a parent category")
                .build();

        category = Category.builder()
                .name("Child Category")
                .description("This is a child category")
                .parentCategory(parentCategory)
                .build();
    }

    @AfterEach
    public void tearDown() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testCreateCategory() {
        session.persist(parentCategory);
        session.persist(category);
        transaction.commit();
        session.beginTransaction();

        Category savedCategory = session.get(Category.class, category.getId());
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
    }

    @Test
    public void testReadCategory() {
        session.persist(parentCategory);
        session.persist(category);
        transaction.commit();
        session.beginTransaction();

        Category foundCategory = session.get(Category.class, category.getId());
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory).isEqualTo(category);
    }

    @Test
    public void testUpdateCategory() {
        session.persist(parentCategory);
        session.persist(category);
        transaction.commit();
        session.beginTransaction();

        Category foundCategory = session.get(Category.class, category.getId());
        foundCategory.setName("Updated Category");
        session.merge(foundCategory);
        transaction.commit();
        session.beginTransaction();

        Category updatedCategory = session.get(Category.class, category.getId());
        assertThat(updatedCategory.getName()).isEqualTo("Updated Category");
    }

    @Test
    public void testDeleteCategory() {
        session.persist(parentCategory);
        session.persist(category);
        transaction.commit();
        session.beginTransaction();

        session.remove(category);
        transaction.commit();
        session.beginTransaction();

        Category deletedCategory = session.get(Category.class, category.getId());
        assertThat(deletedCategory).isNull();
    }
}
package org.market.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E> implements IRepository<K, E> {

    private final Class<E> entityClass;
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        E entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable((entityManager.find(entityClass, id)));
    }

    @Override
    public List<E> findAll() {
        CriteriaQuery<E> criteria = entityManager.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);
        return entityManager.createQuery(criteria).getResultList();
    }

}

package org.market.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IRepository<K extends Serializable, E> {

    E save(E entity);

    E update(E entity);

    void delete(K id);

    Optional<E> findById(K id);

    List<E> findAll();

}

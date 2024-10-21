package org.market.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {

    E save(E entity);

    E update(E entity);

    void delete(K id);

    Optional<E> findById(K id);

    List<E> findAll();

}

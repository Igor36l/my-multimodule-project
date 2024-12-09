package org.market.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.market.entity.Product;
import org.market.repository.filter.ProductFilter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilterProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepository productRepository;

    public List<Product> findByCriteria(ProductFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> user = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(user.get("name"), filter.name()));

        predicates.add(cb.equal(user.get("email"), filter.price()));


        cq.select(user).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}

package org.market.repository;

import org.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "Select * from product p " +
            "where " +
            "CASE " +
            "WHEN :name = '' " +
            "THEN p.price = :price " +
            "WHEN :price is null " +
            "THEN p.name = :name " +
            "ELSE p.name = :name and p.price = :price " +
            "END"
            , nativeQuery = true)
    List<Product> findByNameAndPrice(String name, BigDecimal price);
}

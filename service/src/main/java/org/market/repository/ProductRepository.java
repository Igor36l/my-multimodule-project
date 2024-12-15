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
            "WHEN (:name = '' or :name is null) and :minPrice is null " +
            "THEN 2 = 2 " +
            "WHEN :name = '' or :name is null " +
            "THEN p.price <= :maxPrice and p.price >= :minPrice " +
            "WHEN :minPrice is null " +
            "THEN p.name like %:name% " +
            "ELSE p.name like %:name% and p.price <= :maxPrice and p.price >= :minPrice " +
            "END"
            , nativeQuery = true)
    List<Product> findByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice);
}

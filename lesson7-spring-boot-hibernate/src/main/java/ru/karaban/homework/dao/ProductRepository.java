package ru.karaban.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.karaban.homework.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query(value = """
                    select * from products p
                    where(:titleFilter is null or p.title like :titleFilter)
                    and(:costFilter is null or p.cost like :costFilter)
            """, nativeQuery = true)
    List<Product> productByFilter(String titleFilter, BigDecimal costFilter);

}

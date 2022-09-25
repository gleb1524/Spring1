package ru.karaban.homework.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.karaban.homework.model.Product;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query(value = """
                    select * from products p
                    where(:titleFilter is null or p.title like :titleFilter)
                    and(:costFilter is null or p.cost like :costFilter)
            """
            , countQuery = """
                    select count(*) from products p
                    where(:titleFilter is null or p.title like :titleFilter)
                    and(:costFilter is null or p.cost like :costFilter)
            """
            , nativeQuery = true)
    Page<Product> productByFilter(String titleFilter, BigDecimal costFilter, Pageable p);

}

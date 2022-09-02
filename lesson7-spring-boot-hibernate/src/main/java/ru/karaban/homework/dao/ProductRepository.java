package ru.karaban.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karaban.homework.persist.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByTitleLike(String titleFilter);

    List<Product> findAllByCostLike(BigDecimal costFilter);

}

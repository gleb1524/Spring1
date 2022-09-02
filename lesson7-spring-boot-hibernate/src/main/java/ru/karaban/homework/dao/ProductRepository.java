package ru.karaban.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karaban.homework.persist.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

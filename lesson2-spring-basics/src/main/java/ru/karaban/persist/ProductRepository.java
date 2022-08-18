package ru.karaban.persist;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(long id);

    void insert(Product user);

    void update(Product user);

    void delete(long id);

    long getCount();

    void showProductRepository();
}

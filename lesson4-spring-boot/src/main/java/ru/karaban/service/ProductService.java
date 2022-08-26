package ru.karaban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karaban.dao.ProductDao;
import ru.karaban.persist.Product;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getUserDetails() {
        return productDao.getUserDetails();

    }

    public void insert() {
        productDao.insertProductDb();
    }

    public void deleteProduct(Long id) {
        productDao.deleteProductDb(id);
    }

    public Optional<Product> findById(long id) {
        return productDao.findById(id);
    }

    public void update(Product product) {
        productDao.update(product);
    }

}

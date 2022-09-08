package ru.karaban.homework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karaban.homework.service.FactoryService;
import ru.karaban.homework.persist.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private FactoryService factoryService;


    public Product findById(long id) {
        return factoryService.findById(id);
    }

    public List<Product> findAll() {
        return (List<Product>) factoryService.findAll();
    }

    public void addNewProduct(Product product) {
        factoryService.creatOrUpdateProduct(product);
    }

    public void deleteProduct(long id) {
        factoryService.deleteProduct(id);
    }

    @PostConstruct
    public void initializationTestDateProduct() {
        if (factoryService.productListSize() < 5) {
            for (int i = 1; i <= 20; i++) {
                Product product = new Product("Product" + i, new BigDecimal(i));
                factoryService.initialization(product);
            }
        }
    }
}

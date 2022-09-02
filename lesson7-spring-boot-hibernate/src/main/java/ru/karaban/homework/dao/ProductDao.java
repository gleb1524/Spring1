package ru.karaban.homework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karaban.homework.controller.FactoryController;
import ru.karaban.homework.persist.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private FactoryController factoryController;


    public Product findById(long id) {
        return factoryController.findById(id);
    }

    public List<Product> findAll(){
        return (List<Product>) factoryController.findAll();
    }

    public void addNewProduct(Product product){
        factoryController.creatOrUpdateProduct(product);
    }

    public void deleteProduct(long id){
        factoryController.deleteProduct(id);
    }

    @PostConstruct
    public void initializationTestDateProduct() {
        for (int i = 1; i <= 20; i++) {
            Product product = new Product("Product" + i, new BigDecimal(i));
            factoryController.initialization(product);
        }

    }
}

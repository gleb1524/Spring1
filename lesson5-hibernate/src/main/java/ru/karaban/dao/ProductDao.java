package ru.karaban.dao;

import ru.karaban.controller.FactoryController;
import ru.karaban.model.Product;

import java.time.LocalDate;
import java.util.Optional;


public class ProductDao {

    private FactoryController factoryController = new FactoryController();



    public void init(){

        factoryController.init(new Product("Product1", 1000L, 1000L, 0L, LocalDate.parse("2022-04-15")));
        factoryController.init(new Product("Product2", 10001L, 100L, 10L, LocalDate.parse("2021-11-15")));
        factoryController.init(new Product("Product3", 10002L, 10L, 20L, LocalDate.parse("2022-05-15")));
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//
//        entityManager.persist(new Product("Product1", 1000L, 1000L, 0L, LocalDate.parse("2022-04-15")));
//        entityManager.persist(new Product("Product2", 10001L, 100L, 10L, LocalDate.parse("2021-11-15")));
//        entityManager.persist(new Product("Product3", 10002L, 10L, 20L, LocalDate.parse("2022-05-15")));
//
//        entityManager.getTransaction().commit();
//
//        entityManager.close();
    }
    public void delete(long id){
        factoryController.deleteProduct(id);
    }

    public void update(Product product){
        factoryController.update(product);
    }

    public Optional<Product> findById(Long id){
        return factoryController.findProductById(id);
    }
//
//    public List<Product> getAll(){}
}

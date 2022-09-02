package ru.karaban.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.karaban.homework.persist.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Controller
public class FactoryController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Product findById(long id) {
        return executeForEntityManager(entityManager -> entityManager.find(Product.class, id));
    }

    public List<?> findAll() {
      return   executeForEntityManager(entityManager -> {
            return entityManager.createQuery("select p from Product p ", Product.class).getResultList();
        });
    }

    public void creatOrUpdateProduct(Product product){
        executeInTransaction(entityManager -> {
            entityManager.merge(product);
        });
    }

    public <R> void initialization(R r) {
        executeInTransaction(entityManager -> {
            entityManager.persist(r);
        });
    }

    public void deleteProduct(long id){
        executeInTransaction(entityManager -> {
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
        });
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }
}

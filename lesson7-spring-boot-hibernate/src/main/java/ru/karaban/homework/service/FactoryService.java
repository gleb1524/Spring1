package ru.karaban.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karaban.homework.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class FactoryService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public <R> void initialization(R r) {
        executeInTransaction(entityManager -> {
            entityManager.persist(r);
        });
    }

    public int productListSize() {
        return executeForEntityManager(entityManager ->
                entityManager.createQuery("select p from Product p")
                        .getResultList().size());
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

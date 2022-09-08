package ru.karaban.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karaban.homework.persist.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class FactoryService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Product findById(long id) {
        return executeForEntityManager(entityManager -> entityManager.find(Product.class, id));
    }

    public List<?> findAll() {
        return executeForEntityManager(entityManager -> {
            return entityManager.createQuery("select p from Product p ", Product.class).getResultList();
        });
    }

    public void creatOrUpdateProduct(Product product) {
        executeInTransaction(entityManager -> {
            entityManager.merge(product);
        });
    }

    public <R> void initialization(R r) {
        executeInTransaction(entityManager -> {
            entityManager.persist(r);
        });
    }

    public void deleteProduct(long id) {
        executeInTransaction(entityManager -> {
            entityManager.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id).executeUpdate();
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

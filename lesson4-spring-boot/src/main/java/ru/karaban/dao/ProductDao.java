package ru.karaban.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.karaban.persist.Product;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class ProductDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void insertProductDb() {
        if (countProduct() == 0) {
            executeInTransaction(entityManager -> entityManager.
                    persist(new Product("ProductDB1", 1000L)));
        }
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(executeForEntityManager(entityManager ->
                entityManager.find(Product.class, id)));

    }

    public List<Product> getProductDetails() {
        return executeForEntityManager(entityManager -> entityManager.
                createNamedQuery("findAllProducts", Product.class).getResultList());
    }

    public Long countProduct() {
        return executeForEntityManager(entityManager -> entityManager.
                createNamedQuery("countAllProducts", Long.class).getSingleResult());
    }

    public void deleteProductDb(Long id) {
        executeInTransaction(entityManager ->
                entityManager.createNamedQuery("deleteProductById").
                        setParameter("id", id).executeUpdate());
    }

    public void update(Product product) {
        executeInTransaction(entityManager -> {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

}

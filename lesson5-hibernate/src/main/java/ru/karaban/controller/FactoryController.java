package ru.karaban.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cfg.Configuration;
import ru.karaban.model.Product;
import ru.karaban.model.User;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@Setter
public class FactoryController {

    private EntityManagerFactory entityManagerFactory;

    public FactoryController() {
        this.entityManagerFactory = new Configuration().
                configure("hibernate.cfg.xml").
                buildSessionFactory();

    }

    public <T> void init(T t) {
        executeInTransaction(entityManager -> entityManager.persist(t));
    }

    public void deleteProduct(long id) {
        executeInTransaction(entityManager -> {
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
        });
    }

    public void deleteUser(long id) {
        executeInTransaction(entityManager -> {
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
        });
    }

    public <T> void update(T t) {
        executeInTransaction((entityManager -> {
            entityManager.merge(t);
        }));
    }

    public Optional<Product> findProductById(long id){
       return Optional.ofNullable(executeForEntityManager(entityManager -> entityManager.find(Product.class, id))) ;
    }
    public Optional<User> findUserById(long id){
       return Optional.ofNullable(executeForEntityManager(entityManager -> entityManager.find(User.class, id))) ;
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

package ru.karaban.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karaban.persist.Product;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void insertProductDb() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Product> users = entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();
        if (users.size() == 0) {

            entityManager.persist(new Product("ProductDB1", 1000L));
            entityManager.persist(new Product("ProductDB2", 10001L));
            entityManager.persist(new Product("ProductDB3", 10002L));

        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List getUserDetails() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Product> users = entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();

        return users;
    }

    public void deleteProductDb(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public Product findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Product.class, id);
    }

    public void update(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        if (product.getId() == null) {
            entityManager.persist(new Product(product.getTitle(),
                    product.getCost(),
                    product.getAmount(),
                    product.getDiscount(),
                    product.getDateAdded()
            ));
        } else {
            entityManager.merge(product);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

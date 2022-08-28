import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.karaban.model.Product;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration().
                configure("hibernate.cfg.xml").
                buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        entityManager.getTransaction().begin();
//
//        entityManager.persist(new Product("Product1", 1000L, 1000L, 0L, LocalDate.parse("2022-04-15")));
//        entityManager.persist(new Product("Product2", 10001L, 100L, 10L,  LocalDate.parse("2021-11-15")));
//        entityManager.persist(new Product("Product3", 10002L, 10L, 20L,  LocalDate.parse("2022-05-15")));
//
//        entityManager.getTransaction().commit();

        entityManager.close();
    }
}

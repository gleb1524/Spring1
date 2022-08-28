package ru.karaban;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.karaban.dao.ProductDao;
import ru.karaban.dao.UserDao;
import ru.karaban.model.Product;
import ru.karaban.model.User;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        UserDao userDao = new UserDao();
//        productDao.init();
//        userDao.init();

//        productDao.delete(1L);
//        userDao.delete(1L);

//        userDao.update(new User(2,"New User "));
//        productDao.update(new Product(2L,"Product2new", 10001L, 100L, 10L, LocalDate.parse("2021-08-15")));

//        productDao.findById(2L);
//        userDao.findById(2L);
    }
}

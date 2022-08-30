package ru.karaban.dao;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import ru.karaban.controller.FactoryController;
import ru.karaban.model.Product;
import ru.karaban.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    FactoryController factoryController = new FactoryController();

    public void init() {
        List<Product> productsUser1 = new ArrayList<>();
        List<Product> productsUser2 = new ArrayList<>();
        List<Product> productsUser3 = new ArrayList<>();

        productsUser1.add(factoryController.findProductById(1).get());
        productsUser1.add(factoryController.findProductById(2).get());
        productsUser1.add(factoryController.findProductById(3).get());

        productsUser2.add(factoryController.findProductById(1).get());
        productsUser2.add(factoryController.findProductById(3).get());

        productsUser3.add(factoryController.findProductById(2).get());
        productsUser3.add(factoryController.findProductById(3).get());

        User user1 = new User("Mikk", productsUser1);
        User user2 = new User("Vova", productsUser2);
        User user3 = new User("Lada", productsUser3);


        factoryController.init(user1);
        factoryController.init(user2);
        factoryController.init(user3);

    }


    public void delete(long id) {
        factoryController.deleteUser(id);
    }

    public void update(User user) {
        factoryController.update(user);
    }

    public Optional<User> findById(Long id) {
        return factoryController.findUserById(id);
    }
//
//    public List<Product> getAll(){}
}

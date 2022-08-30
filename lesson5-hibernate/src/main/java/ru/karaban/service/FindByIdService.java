package ru.karaban.service;

import org.springframework.stereotype.Service;
import ru.karaban.controller.FactoryController;
import ru.karaban.model.Product;
import ru.karaban.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class FindByIdService {
    FactoryController factoryController = new FactoryController();

    List<User> users = factoryController.getEntityManager().
            createQuery("select u from User u " +
            "join fetch u.products", User.class).getResultList();

    List<Product> products = factoryController.getEntityManager().
            createQuery("select p from Product p " +
                    "join fetch p.users", Product.class).getResultList();

    public void getProductForUserId(int id){
                List<Product> products = users.get(id).getProducts();
        products.forEach(System.out::println);
    }

    public void getUserForProductId(int id){
        List<User> users = products.get(id).getUsers();
        users.forEach(System.out::println);
    }
}

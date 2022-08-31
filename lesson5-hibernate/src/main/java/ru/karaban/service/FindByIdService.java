package ru.karaban.service;

import org.springframework.stereotype.Service;
import ru.karaban.controller.FactoryController;
import ru.karaban.model.Product;
import ru.karaban.model.User;

import java.util.ArrayList;
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

    public void getProductForUserId(int id) {
        List<Product> productsBayUser = new ArrayList<>();
        for (User user : users) {
            if (user.getId() == id) {
                productsBayUser = user.getProducts();
            }
        }
        System.out.println("Products fo user with id: " + id);
        productsBayUser.forEach(product -> System.out.println("Product id:" +
                product.getId() + "\n" + "Product title: " + product.getTitle()));
    }

    public void getUserForProductId(int id) {
        List<User> usersWithProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.getId() == id) {
                usersWithProduct = product.getUsers();
            }
        }
        System.out.println("Users who have a product with id: " + id);
        usersWithProduct.forEach(user -> System.out.println("[User id: " +
                user.getId() + "||" + "User name: " + user.getName() + "]"));
    }

}

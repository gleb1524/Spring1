package ru.karaban;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.karaban.config.AppConfiguration;
import ru.karaban.controller.FactoryController;

import ru.karaban.dao.ProductDao;
import ru.karaban.dao.UserDao;
import ru.karaban.model.Product;
import ru.karaban.service.FindByIdService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        FindByIdService findByIdService = context.getBean("findByIdService", FindByIdService.class);
        FactoryController factoryController = context.getBean("factoryController", FactoryController.class);

//        productDao.init();
//        userDao.init();


        findByIdService.getProductForUserId(1);
        findByIdService.getUserForProductId(2);
    }
}

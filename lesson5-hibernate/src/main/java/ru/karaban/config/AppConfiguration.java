package ru.karaban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.karaban.controller.FactoryController;
import ru.karaban.dao.ProductDao;
import ru.karaban.dao.UserDao;
import ru.karaban.service.FindByIdService;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductDao productDao(){return new ProductDao();}

    @Bean
    public UserDao userDao(){return new UserDao();}

    @Bean
    public FactoryController factoryController(){ return new FactoryController();}

    @Bean
    FindByIdService findByIdService(){ return new FindByIdService();}
}

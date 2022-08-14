package ru.karaban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.karaban.Cart;
import ru.karaban.ProductService;
import ru.karaban.persist.ProductRepository;
import ru.karaban.persist.ProductRepositoryImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductRepository productRepository(){
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository){
        return new ProductService(productRepository);
    }

    @Bean
    @Scope("prototype")
    public Cart cart() {
        return new Cart();
    }
}

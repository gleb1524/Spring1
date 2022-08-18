package ru.karaban;

import org.springframework.stereotype.Service;
import ru.karaban.persist.Product;
import ru.karaban.persist.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository repository){
        this.productRepository = repository;
    }

    public void insert(Product product) {
        this.productRepository.insert(product);
    }

    public int findAll() {
        return this.productRepository.findAll().size();
    }

    public ProductRepository getProductRepository(){
        return this.productRepository;
    }
}

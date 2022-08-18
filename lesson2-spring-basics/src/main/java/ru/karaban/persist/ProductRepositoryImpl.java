package ru.karaban.persist;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll(){
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id){
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }

    @Override
    public long getCount() {
        return productMap.size();
    }
    @Override
    public void showProductRepository() {
        List<Product> cartList = new ArrayList<>(productMap.values());
        for (Product product : cartList) {
            System.out.println("[Id: " + product.getId() + "| Title: " + product.getTitle()
                    + "| Cost: " + product.getCost() + " ]");
        }
    }
}

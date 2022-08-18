package ru.karaban;

import ru.karaban.persist.Product;
import ru.karaban.persist.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {

    private ProductRepository productRepository;
    private final Map<Long, Product> cartMap = new ConcurrentHashMap<>();

    public void addProduct(Product product) {
        cartMap.put(product.getId(), product);
    }

    public void deleteProduct(Long id) {
        cartMap.remove(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(cartMap.values());
    }

    public void showCart() {
        List<Product> cartList = new ArrayList<>(cartMap.values());
        for (Product product : cartList) {
            System.out.println("[Id: " + product.getId() + "| Title: " + product.getTitle()
                    + "| Cost: " + product.getCost() + " ]");
        }
        if(cartList.size()==0){
            System.out.println("Корзина пуста");
        }
    }

    public Set<Long> keySet(){
        return cartMap.keySet();
    }
}

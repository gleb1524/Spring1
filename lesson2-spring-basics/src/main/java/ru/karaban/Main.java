package ru.karaban;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.karaban.config.AppConfiguration;
import ru.karaban.persist.Product;
import ru.karaban.persist.ProductRepositoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        Cart cart = context.getBean("cart", Cart.class);
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < 6; i++) {
            productService.insert(new Product("Product" + i, (int) (Math.random() * 1000)));
        }

        productService.getProductRepository().showProductRepository();
        while (true) {
            System.out.println("Если хотите добавить товар в корзину, введите id товара. \n" +
                    "Для удалиния товара введите -id товара.");
            long id;
            String[] inputId = scanner.nextLine().split("-");
            try {
                if(inputId.length==1){
                id = Long.parseLong(inputId[0]);
                cart.addProduct(productService.getProductRepository().findById(id));
                System.out.println("Товар " + productService.getProductRepository().findById(id).getTitle() +
                        " успешно добавлен в карзину.");
                System.out.println("Ваша корзина: ");
                cart.showCart();
                }else{
                    id = Long.parseLong(inputId[1]);
                    if(cart.keySet().contains(id)){
                        cart.deleteProduct(id);
                        System.out.println("Товар " +
                                productService.getProductRepository().findById(id).getTitle() + " удалён из корзины");
                    }else{
                        System.out.println("Товар с id " + id + " не найден в корзине.");
                    }
                    System.out.println("Ваша корзина: ");
                    cart.showCart();
                }
            } catch (Exception e) {
                System.err.println("Некоректный запрос. Проверьте введёные параметры");
            }
        }
    }
}

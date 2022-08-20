package ru.karaban.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karaban.persist.Product;
import ru.karaban.persist.ProductRepository;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product("", 0));
        return "product_form";
    }

    @PostMapping
    public String saveProduct(Product product) {
        productRepository.update(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id){
       productRepository.delete(id);
        return "redirect:/product";
    }
//    @PostMapping("/new")
//    public String addProduct(Product product) {
//        productRepository.update(product);
//        return "redirect:/product";
//    }

}

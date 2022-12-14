package ru.karaban.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karaban.persist.Product;
import ru.karaban.persist.ProductRepository;

@Controller
@RequestMapping("/add-product")
@RequiredArgsConstructor
public class AddProductController {

    private final ProductRepository productRepository;

    @GetMapping()
    public String addProduct(Model model){
//        model.addAttribute("product", productRepository.findById(4));
        return "add_product";
    }

    @PostMapping ()
    public String addProduct(Product product){
        productRepository.insert(product);
        return "redirect:/product";
    }
}

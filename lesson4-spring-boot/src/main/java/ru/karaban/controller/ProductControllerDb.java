package ru.karaban.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.karaban.persist.Product;
import ru.karaban.service.ProductService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/productDb")
@RequiredArgsConstructor
public class ProductControllerDb {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String listPageDb(Model model) {
        model.addAttribute("products", productService.getProductDetails());
        return "product";
    }

    @GetMapping("/delete/{id}")
    public String deleteByIdDb(@PathVariable long id) {
        productService.deleteProduct(id);
        return "redirect:/productDb";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", 0));
        return "product_form";
    }

    @PostMapping()
    public String updateProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.update(product);
        return "redirect:/productDb";
    }
}

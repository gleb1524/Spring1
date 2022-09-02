package ru.karaban.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.karaban.homework.dao.ProductDao;
import ru.karaban.homework.persist.Product;

import java.math.BigDecimal;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productDao.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productDao.findById(id));
        return "product_form";
    }

    @PostMapping
    public String creatOrUpdate(Product product){
        productDao.addNewProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", BigDecimal.ZERO));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteByIdDb(@PathVariable long id) {
        productDao.deleteProduct(id);
        return "redirect:/product";
    }
}

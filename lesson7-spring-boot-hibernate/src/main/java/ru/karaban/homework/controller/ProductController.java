package ru.karaban.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karaban.homework.dao.ProductRepository;
import ru.karaban.homework.persist.Product;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

//    @Autowired
//    private ProductDao productDao;
    private final ProductRepository productDao;

    @GetMapping
    public String listPage(@RequestParam Optional<String> titleFilter,
                           @RequestParam Optional<BigDecimal> costFilter,
                           Model model) {
        if(titleFilter.isEmpty() || titleFilter.get().isBlank()){
            model.addAttribute("products", productDao.findAll());
        }else{
            model.addAttribute("products",
                    productDao.findAllByTitleLike("%" + titleFilter.get() +"%"));
        }
        if(costFilter.isEmpty()){
            model.addAttribute("products", productDao.findAll());
        }else{
            model.addAttribute("products",
                    productDao.findAllByCostLike( costFilter.get() ));
        }

        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productDao.findById(id));
        return "product_form";
    }

    @PostMapping
    public String creatOrUpdate(Product product){
        productDao.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", BigDecimal.ZERO));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteByIdDb(@PathVariable long id) {
        productDao.deleteById(id);
        return "redirect:/product";
    }
}

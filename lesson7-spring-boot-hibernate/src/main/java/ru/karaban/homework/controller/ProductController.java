package ru.karaban.homework.controller;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karaban.homework.dao.ProductRepository;
import ru.karaban.homework.persist.Product;
import ru.karaban.homework.persist.QProduct;

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

//    @GetMapping
//    public String listPage(@RequestParam(required = false) String titleFilter,
//                           @RequestParam(required = false) BigDecimal costFilter,
//                                                      Model model) {
//        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter + "%";
//        costFilter = costFilter == null || titleFilter != null ? null : costFilter;
//        model.addAttribute("products", productDao.productByFilter(titleFilter, costFilter));
//
//        return "product";
//    }
    @GetMapping
    public String listPage(@RequestParam(required = false) String titleFilter,
                           @RequestParam(required = false) BigDecimal costFilter,
                                                      Model model) {

        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();

        if(titleFilter != null && !titleFilter.isBlank()){
            predicate.and(product.title.contains(titleFilter.trim()));
        }

        if(costFilter != null){
            predicate.and(product.cost.eq(costFilter));
        }

        model.addAttribute("products", productDao.findAll(predicate));
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

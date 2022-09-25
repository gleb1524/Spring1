package ru.karaban.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karaban.homework.model.dto.ProductDto;
import ru.karaban.homework.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    //    @Autowired
//    private ProductDao productDao;
    private final ProductService service;

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
                           @RequestParam(required = false) Optional<Integer> page,
                           @RequestParam(required = false) Optional<Integer> size,
                           Model model) {
        int pageValue = page.orElse(1) - 1;
        int sizeValue = size.orElse(5);

        model.addAttribute("products", service.findAllByFilter(titleFilter, costFilter, pageValue, sizeValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", service.findProductById(id));
        return "product_form";
    }

    @PostMapping()
    public String creatOrUpdate(@Valid @ModelAttribute("product") ProductDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        service.save(dto);
        return "redirect:/product";
    }


    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteByIdDb(@PathVariable long id) {
        service.deleteProductById(id);
        return "redirect:/product";
    }
}

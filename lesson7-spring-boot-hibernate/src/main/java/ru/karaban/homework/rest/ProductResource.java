package ru.karaban.homework.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karaban.homework.model.dto.ProductDto;
import ru.karaban.homework.service.ProductService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> listPage(@RequestParam(required = false) String titleFilter,
                                     @RequestParam(required = false) BigDecimal costFilter,
                                     @RequestParam(required = false) Optional<Integer> page,
                                     @RequestParam(required = false) Optional<Integer> size
                           ) {
        return requestOptimization(titleFilter, costFilter, page, size);
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id) {
        ProductDto productDto = productService.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDto;
    }

    @GetMapping("/new")
    public ProductDto addNewProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return productDto;
    }

    @GetMapping("/delete/{id}")
    public List<ProductDto> deleteByIdDb(@PathVariable long id,
                                         @RequestParam(required = false) String titleFilter,
                                         @RequestParam(required = false) BigDecimal costFilter,
                                         @RequestParam(required = false) Optional<Integer> page,
                                         @RequestParam(required = false) Optional<Integer> size) {
        productService.deleteProductById(id);
       return requestOptimization(titleFilter, costFilter, page, size);
    }

    private List<ProductDto> requestOptimization(String titleFilter,BigDecimal costFilter, Optional<Integer> page, Optional<Integer> size){
        int pageValue = page.orElse(1) - 1;
        int sizeValue = size.orElse(5);

        Page<ProductDto> allByFilter = productService.findAllByFilter(titleFilter, costFilter, pageValue, sizeValue);
        List<ProductDto> dto = allByFilter.stream().toList();
        return dto;
    }
}

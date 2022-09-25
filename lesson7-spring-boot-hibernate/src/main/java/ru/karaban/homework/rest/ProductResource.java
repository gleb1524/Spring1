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
    private Long productDtoId;

    @GetMapping
    public Page<ProductDto> listPage(@RequestParam(required = false) String titleFilter,
                                     @RequestParam(required = false) BigDecimal costFilter,
                                     @RequestParam(required = false) Optional<Integer> page,
                                     @RequestParam(required = false) Optional<Integer> size
                           ) {
        return requestOptimization(titleFilter, costFilter, page, size);
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") Long id) {
        productDtoId = id;
        ProductDto productDto = productService.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDto;
    }


    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        if(productDto.getId() != null){
            throw new IllegalArgumentException("Created product shouldn`t have id");
        }
        productService.save(productDto);
        return productDto;
    }
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        try {
            productDto.setId(productDtoId);
            productService.save(productDto);
        } catch (RuntimeException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return productDto;
    }


    @DeleteMapping ()
    public Page<ProductDto> deleteByIdDb(@RequestParam Long id,
                                         @RequestParam(required = false) String titleFilter,
                                         @RequestParam(required = false) BigDecimal costFilter,
                                         @RequestParam(required = false) Optional<Integer> page,
                                         @RequestParam(required = false) Optional<Integer> size) {
        productService.deleteProductById(id);
       return requestOptimization(titleFilter, costFilter, page, size);
    }

    private Page<ProductDto> requestOptimization(String titleFilter,BigDecimal costFilter, Optional<Integer> page, Optional<Integer> size){
        int pageValue = page.orElse(1) - 1;
        int sizeValue = size.orElse(5);

        Page<ProductDto> allByFilter = productService.findAllByFilter(titleFilter, costFilter, pageValue, sizeValue);

        return allByFilter;
    }



}

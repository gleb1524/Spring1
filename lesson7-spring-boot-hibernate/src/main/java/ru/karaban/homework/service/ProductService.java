package ru.karaban.homework.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.homework.dao.ProductRepository;
import ru.karaban.homework.model.Product;
import ru.karaban.homework.model.QProduct;
import ru.karaban.homework.model.dto.ProductDto;
import ru.karaban.homework.model.mapper.ProductDtoMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductDtoMapper mapper;

    public List<ProductDto> findAllByFilter(String titleFilter, BigDecimal costFilter) {

        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();
        if (titleFilter != null && !titleFilter.isBlank()) {
            predicate.and(product.title.contains(titleFilter.trim()));
        }

        if (costFilter != null) {
            predicate.and(product.cost.eq(costFilter));
        }
        return StreamSupport.stream(repository.findAll(predicate).spliterator(), true)
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findProductById(Long id) {

        return repository.findById(id).map(mapper::map);
    }

    public void save(ProductDto dto) {

        repository.save(mapper.map(dto));
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

}

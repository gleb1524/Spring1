package ru.karaban.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.karaban.homework.dao.ProductRepository;
import ru.karaban.homework.model.dto.ProductDto;
import ru.karaban.homework.model.mapper.ProductDtoMapper;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductDtoMapper mapper;

    public Page<ProductDto> findAllByFilter(String titleFilter, BigDecimal costFilter, int page, int size) {
        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter.trim() + "%";
        return repository.productByFilter(titleFilter, costFilter, PageRequest.of(page, size)).map(mapper::map);
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

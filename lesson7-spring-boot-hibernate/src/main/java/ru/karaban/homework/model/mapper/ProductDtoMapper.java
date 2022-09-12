package ru.karaban.homework.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.karaban.homework.model.Product;
import ru.karaban.homework.model.dto.ProductDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);
}

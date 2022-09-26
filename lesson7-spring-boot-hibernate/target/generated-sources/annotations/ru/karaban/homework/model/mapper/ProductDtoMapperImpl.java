package ru.karaban.homework.model.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.karaban.homework.model.Product;
import ru.karaban.homework.model.dto.ProductDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-20T16:58:52+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class ProductDtoMapperImpl implements ProductDtoMapper {

    @Override
    public ProductDto map(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getTitle() != null ) {
            productDto.setTitle( product.getTitle() );
        }
        if ( product.getCost() != null ) {
            productDto.setCost( product.getCost() );
        }
        if ( product.getId() != null ) {
            productDto.setId( product.getId() );
        }

        return productDto;
    }

    @Override
    public Product map(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDto.getId() != null ) {
            product.setId( productDto.getId() );
        }
        if ( productDto.getTitle() != null ) {
            product.setTitle( productDto.getTitle() );
        }
        if ( productDto.getCost() != null ) {
            product.setCost( productDto.getCost() );
        }

        return product;
    }
}

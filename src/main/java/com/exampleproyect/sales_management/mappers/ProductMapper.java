package com.exampleproyect.sales_management.mappers;

import com.exampleproyect.sales_management.domain.models.entities.Product;
import com.exampleproyect.sales_management.dto.ProductDto;

public class ProductMapper {

    
    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());

        return productDto;
    } 

}

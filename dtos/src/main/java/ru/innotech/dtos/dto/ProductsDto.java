package ru.innotech.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class ProductsDto {

    private Set<ProductDto> productDtoSet = new HashSet<>();

    public ProductsDto() {
    }

    public void addProductDto(ProductDto productDto) {
        productDtoSet.add(productDto);
    }

}

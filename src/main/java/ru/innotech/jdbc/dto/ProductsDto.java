package ru.innotech.jdbc.dto;

import lombok.Getter;
import ru.innotech.jdbc.entities.Product;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ProductsDto {

    private Set<ProductDto> productDtoSet = new HashSet<>();

    public ProductsDto() {
    }

    public ProductsDto(Set<Product> productSet){
        Set<ProductDto> productDtoSet = new HashSet<>();
        for (Product product : productSet) {
            productDtoSet.add(new ProductDto(product));
        }
        this.productDtoSet = productDtoSet;
    }

    public void addProductDto(ProductDto productDto){
        productDtoSet.add(productDto);
    }

}

package ru.innotech.products.entities;

import lombok.Data;
import ru.innotech.dtos.dto.ProductDto;
import ru.innotech.dtos.dto.ProductsDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Product {
    private Long id;
    private String accNumber;
    private BigDecimal accRest;
    private ProductType prodType;

    public Product() {
    }

    public Product(Long id, String accNumber, BigDecimal accRest, ProductType prodType) {
        this.id = id;
        this.accNumber = accNumber;
        this.accRest = accRest;
        this.prodType = prodType;
    }

    public Product(String accNumber, BigDecimal accRest, ProductType prodType) {
        this(null, accNumber, accRest, prodType);
    }

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(this.getId());
        productDto.setAccNumber(this.getAccNumber());
        productDto.setAccRest(this.getAccRest());
        productDto.setProdTypeName(this.getProdType().toString());
        return productDto;
    }

    public static ProductsDto createProductsDto(Set<Product> productSet) {
        Set<ProductDto> productDtoSet = new HashSet<>();
        for (Product product : productSet) {
            productDtoSet.add(product.toProductDto());
        }
        return new ProductsDto(productDtoSet);
    }

}

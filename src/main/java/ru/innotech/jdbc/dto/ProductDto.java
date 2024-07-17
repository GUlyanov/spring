package ru.innotech.jdbc.dto;

import lombok.Data;
import ru.innotech.jdbc.entities.Product;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String accNumber;
    private BigDecimal accRest;
    private String prodTypeName;

    public ProductDto(Long id, String accNumber, BigDecimal accRest, String prodTypeName) {
        this.id = id;
        this.accNumber = accNumber;
        this.accRest = accRest;
        this.prodTypeName = prodTypeName;
    }

    public ProductDto(Product product) {
        this(
                product.getId(),
                product.getAccNumber(),
                product.getAccRest(),
                product.getProdType().toString()
        );
    }

    public ProductDto() {
    }
}

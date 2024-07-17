package ru.innotech.jdbc.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String accNumber;
    private BigDecimal accRest;
    private ProductType prodType;

    public Product() {
    }

    public Product(String accNumber, BigDecimal accRest, ProductType prodType) {
        this.accNumber = accNumber;
        this.accRest = accRest;
        this.prodType = prodType;
    }
}

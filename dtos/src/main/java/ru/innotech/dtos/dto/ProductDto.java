package ru.innotech.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String accNumber;
    private BigDecimal accRest;
    private String prodTypeName;

    public ProductDto() {
    }
}

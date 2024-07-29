package ru.innotech.products.exceptions;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductAccInsufficientFundsException extends RuntimeException {
    private Long productId;
    private Long userId;
    private BigDecimal accRest;

    public ProductAccInsufficientFundsException(String message, Long productId, Long userId, BigDecimal accRest) {
        super(message);
        this.productId = productId;
        this.userId = userId;
        this.accRest = accRest;
    }

}

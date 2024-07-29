package ru.innotech.products.exceptions;

import lombok.Getter;

@Getter
public class ProductByIdNotFoundException extends RuntimeException {
    private final Long productId;

    public ProductByIdNotFoundException(String message, Long productId) {
        super(message);
        this.productId = productId;
    }

}

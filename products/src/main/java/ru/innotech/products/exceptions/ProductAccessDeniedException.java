package ru.innotech.products.exceptions;

import lombok.Getter;

@Getter
public class ProductAccessDeniedException extends RuntimeException {
    private final Long userId;
    private final Long productId;

    public ProductAccessDeniedException(String message, Long userId, Long productId) {
        super(message);
        this.userId = userId;
        this.productId = productId;
    }
}

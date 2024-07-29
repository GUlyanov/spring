package ru.innotech.products.exceptions;

import lombok.Getter;

@Getter
public class ProductsByUserIdNotFoundException extends RuntimeException {
    private final Long userId;

    public ProductsByUserIdNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

}

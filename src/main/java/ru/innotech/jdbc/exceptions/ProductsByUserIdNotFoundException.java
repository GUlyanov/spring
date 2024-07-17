package ru.innotech.jdbc.exceptions;

public class ProductsByUserIdNotFoundException extends RuntimeException {
    private Long userId;

    public ProductsByUserIdNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}

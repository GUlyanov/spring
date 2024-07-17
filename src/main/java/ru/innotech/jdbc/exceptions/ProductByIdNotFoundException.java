package ru.innotech.jdbc.exceptions;

public class ProductByIdNotFoundException extends RuntimeException{
    private final Long productId;
    public ProductByIdNotFoundException(String message, Long productId) {
        super(message);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}

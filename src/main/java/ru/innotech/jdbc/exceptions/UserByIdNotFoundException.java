package ru.innotech.jdbc.exceptions;

public class UserByIdNotFoundException extends RuntimeException{
    private final Long userId;

    public UserByIdNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}

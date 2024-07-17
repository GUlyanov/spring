package ru.innotech.jdbc.exceptions;

import lombok.Getter;

@Getter
public class UserByIdNotFoundException extends RuntimeException{
    private final Long userId;

    public UserByIdNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

}

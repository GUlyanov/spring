package ru.innotech.jdbc.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    ACCOUNT("Счет"),
    CARD("Карта")
    ;
    private final String name;
}

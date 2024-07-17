package ru.innotech.jdbc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.innotech.jdbc.exceptions.ProductByIdNotFoundException;
import ru.innotech.jdbc.exceptions.ProductsByUserIdNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    // 2.Код класса продукта не найден
    @ExceptionHandler(ProductByIdNotFoundException.class)
    public ResponseEntity<String> excProductByIdNotFoundHandler(ProductByIdNotFoundException ex){
        String sMess = "Продукт с ид =  <%s> не найден";
        sMess = sMess.formatted(ex.getProductId());
        return ResponseEntity
                .status(404)  // NOT FOUND
                .body(sMess);
    }
    @ExceptionHandler(ProductsByUserIdNotFoundException.class)
    public ResponseEntity<String> excProductsByUserIdNotFoundHandler(ProductsByUserIdNotFoundException ex){
        String sMess = "Продукты с ид пользователя =  <%s> не найдены";
        sMess = sMess.formatted(ex.getUserId());
        return ResponseEntity
                .status(404)  // NOT FOUND
                .body(sMess);
    }
}

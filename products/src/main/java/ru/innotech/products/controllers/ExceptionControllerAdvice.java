package ru.innotech.products.controllers;

import jakarta.annotation.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.innotech.products.exceptions.ProductAccInsufficientFundsException;
import ru.innotech.products.exceptions.ProductAccessDeniedException;
import ru.innotech.products.exceptions.ProductByIdNotFoundException;
import ru.innotech.products.exceptions.ProductsByUserIdNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ProblemDetail problemDetail = (ProblemDetail) body;
        if (body == null)
            problemDetail = ProblemDetail.forStatusAndDetail(statusCode, ex.getMessage());
        problemDetail.setProperty("errorMessage", ex.getMessage());
        // problemDetail.setProperty("errorStackTrace",ex.getStackTrace());
        return super.handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
    }

    // 1.Продукт с заданным ид не найден
    @ExceptionHandler(ProductByIdNotFoundException.class)
    public ResponseEntity<Object> excProductByIdNotFoundHandler(ProductByIdNotFoundException ex, WebRequest request) {
        String sMess = "10. Продукт с ид =  <%s> не найден";
        sMess = sMess.formatted(ex.getProductId());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, sMess);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 2.Продукты с заданным ид пользователя не найдены
    @ExceptionHandler(ProductsByUserIdNotFoundException.class)
    public ResponseEntity<Object> excProductsByUserIdNotFoundHandler(ProductsByUserIdNotFoundException ex, WebRequest request) {
        String sMess = "20. Продукты с ид пользователя =  <%s> не найдены";
        sMess = sMess.formatted(ex.getUserId());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, sMess);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 3.Пользователь пытается выбрать чужой продукт по ид
    @ExceptionHandler(ProductAccessDeniedException.class)
    public ResponseEntity<Object> excProductAccessDeniedHandler(ProductAccessDeniedException ex, WebRequest request) {
        String sMess = "30. У пользователя с ид <%s> нет доступа к продукту с ид <%s>";
        sMess = sMess.formatted(ex.getUserId(), ex.getProductId());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, sMess);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 4.Недостаточно средств на счете продукта
    @ExceptionHandler(ProductAccInsufficientFundsException.class)
    public ResponseEntity<Object> excProductAccInsufficientFundsHandler(ProductAccInsufficientFundsException ex, WebRequest request) {
        String sMess = "40. У пользователя с ид <%s> недостаточно средств на счете продукта с ид <%s>. Остаток равен <%.2f>";
        sMess = sMess.formatted(ex.getUserId(), ex.getProductId(), ex.getAccRest().doubleValue());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, sMess);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}

package ru.innotech.payments.controllers;

import jakarta.annotation.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ProblemDetail problemDetail = (ProblemDetail) body;
        if (body == null)
            problemDetail = ProblemDetail.forStatusAndDetail(statusCode, ex.getMessage());
        problemDetail.setProperty("errorMessage", ex.getMessage());
        return super.handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(), "Ошибка взаимодействия с продуктовым сервисом");
        return handleExceptionInternal(e, problemDetail, new HttpHeaders(), e.getStatusCode(), request);
    }


}
package ru.innotech.payments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    private final ObjectMapper mapper;

    public ExceptionControllerAdvice(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ProblemDetail problemDetail = (ProblemDetail) body;
        if (body == null)
            problemDetail = ProblemDetail.forStatusAndDetail(statusCode, ex.getMessage());
        problemDetail.setProperty("errorMessage", ex.getMessage());
        return super.handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e, WebRequest request) throws JsonProcessingException {
        String mess1 = mapper.readValue(e.getResponseBodyAsString(), ProblemDetail.class).getDetail();
        mess1 = (mess1 == null) ? "Ошибка взаимодействия с продуктовым сервисом" : mess1;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(), mess1);
        return handleExceptionInternal(e, problemDetail, new HttpHeaders(), e.getStatusCode(), request);
    }


}
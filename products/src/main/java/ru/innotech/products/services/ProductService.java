package ru.innotech.products.services;

import ru.innotech.dtos.dto.PaymentReqDto;
import ru.innotech.dtos.dto.PaymentRespDto;
import ru.innotech.products.entities.Product;

import java.util.Set;

public interface ProductService {
    Product getProductById(Long productId);

    Set<Product> getProductsByUserId(Long userId);

    Set<Product> findAll();

    Boolean productIsAccessed(Long userId, Long productId);

    PaymentRespDto doPayment(PaymentReqDto paymentReqDto);

    void save(Product product);

    void delete(Product product);

    void deleteAll();

}

package ru.innotech.payments.services;

import ru.innotech.dtos.dto.PaymentDto;
import ru.innotech.dtos.dto.ProductsDto;

import java.math.BigDecimal;

public interface PaymentService {
    ProductsDto getProductsByUserId(Long userId);

    PaymentDto doPayment(Long userId, Long productId, BigDecimal sum);
}

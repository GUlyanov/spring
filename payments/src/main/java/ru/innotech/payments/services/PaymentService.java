package ru.innotech.payments.services;

import ru.innotech.dtos.dto.PaymentReqDto;
import ru.innotech.dtos.dto.PaymentRespDto;
import ru.innotech.dtos.dto.ProductsDto;

import java.math.BigDecimal;

public interface PaymentService {
    ProductsDto getProductsByUserId(Long userId);

    PaymentRespDto doPayment(PaymentReqDto payReqDto);
}

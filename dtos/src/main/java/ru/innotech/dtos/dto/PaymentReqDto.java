package ru.innotech.dtos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentReqDto {
    private Long userId;
    private Long productId;
    private BigDecimal sum;
}

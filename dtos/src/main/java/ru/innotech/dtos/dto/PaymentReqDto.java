package ru.innotech.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PaymentReqDto {
    private Long userId;
    private Long productId;
    private BigDecimal sum;
}

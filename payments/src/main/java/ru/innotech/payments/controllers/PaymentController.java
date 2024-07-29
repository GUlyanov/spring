package ru.innotech.payments.controllers;

import org.springframework.web.bind.annotation.*;
import ru.innotech.dtos.dto.PaymentDto;
import ru.innotech.dtos.dto.ProductsDto;
import ru.innotech.payments.services.PaymentService;

import java.math.BigDecimal;

@RestController
public class PaymentController {

    private final PaymentService productService;

    public PaymentController(PaymentService productService) {
        this.productService = productService;
    }

    @GetMapping("products/user/{userId}")
    public ProductsDto productsByUserId(@PathVariable Long userId) {
        return productService.getProductsByUserId(userId);
    }

    @PostMapping("payments/user/{userId}/product/{productId}/sum/{sum}")
    public PaymentDto doPayment(@PathVariable Long userId,
                                @PathVariable Long productId,
                                @PathVariable BigDecimal sum) {
        return productService.doPayment(userId, productId, sum);
    }


}

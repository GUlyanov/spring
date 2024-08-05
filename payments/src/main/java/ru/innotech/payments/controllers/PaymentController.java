package ru.innotech.payments.controllers;

import org.springframework.web.bind.annotation.*;
import ru.innotech.dtos.dto.PaymentReqDto;
import ru.innotech.dtos.dto.PaymentRespDto;
import ru.innotech.dtos.dto.ProductsDto;
import ru.innotech.payments.services.PaymentService;

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

    @PostMapping("payment")
    public PaymentRespDto doPayment(@RequestBody PaymentReqDto payReqDto) {
        return productService.doPayment(payReqDto);
    }


}

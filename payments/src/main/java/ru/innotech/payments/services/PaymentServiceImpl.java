package ru.innotech.payments.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.innotech.dtos.dto.PaymentDto;
import ru.innotech.dtos.dto.ProductsDto;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${integrations.executors.products-executor-client.url}")
    private String productsUrl;

    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductsDto getProductsByUserId(Long userId) {
        ResponseEntity<ProductsDto> entity = restTemplate.getForEntity(productsUrl + "/user/{userId}", ProductsDto.class, userId);
        return entity.getBody();
    }

    @Override
    public PaymentDto doPayment(Long userId, Long productId, BigDecimal sum) {
        String url = productsUrl + "/user/{userId}/product/{productId}/sum/{sum}";
        //PaymentDto paymentDto = restTemplate.postForObject(url, null, PaymentDto.class, userId, productId, sum);
        //return paymentDto;
        ResponseEntity<PaymentDto> entity = restTemplate.postForEntity(url, null, PaymentDto.class, userId, productId, sum);
        return entity.getBody();
    }
}

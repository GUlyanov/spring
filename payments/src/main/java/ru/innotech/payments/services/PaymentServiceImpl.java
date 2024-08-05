package ru.innotech.payments.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.innotech.dtos.dto.PaymentReqDto;
import ru.innotech.dtos.dto.PaymentRespDto;
import ru.innotech.dtos.dto.ProductsDto;
import ru.innotech.payments.config.properties.ExecutorsProperties;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final String productsUrl;
    private final RestTemplate restTemplate;

    public PaymentServiceImpl(ExecutorsProperties executorsProperties, RestTemplate restTemplate) {
        productsUrl = executorsProperties.getProductsExecutorClient().getUrl();
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductsDto getProductsByUserId(Long userId) {
        ResponseEntity<ProductsDto> entity = restTemplate.getForEntity(productsUrl + "/user/{userId}", ProductsDto.class, userId);
        return entity.getBody();
    }

    @Override
    public PaymentRespDto doPayment(PaymentReqDto payReqDto) {
        String url = productsUrl + "/payment";
        ResponseEntity<PaymentRespDto> entity = restTemplate.postForEntity(url, payReqDto, PaymentRespDto.class);
        return entity.getBody();
    }
}

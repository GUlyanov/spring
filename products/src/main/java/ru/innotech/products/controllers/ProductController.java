package ru.innotech.products.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innotech.dtos.dto.PaymentDto;
import ru.innotech.dtos.dto.ProductDto;
import ru.innotech.dtos.dto.ProductsDto;
import ru.innotech.products.entities.Product;
import ru.innotech.products.servicies.ProductService;

import java.math.BigDecimal;
import java.util.Set;

@RestController("productController")
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // проверка сервера
    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> productById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        ProductDto productDto = product.toProductDto();
        return ResponseEntity
                .status(200)
                .body(productDto);
    }

    @GetMapping("/user/{userId}")
    public ProductsDto productsByUserId(@PathVariable Long userId) {
        Set<Product> products = productService.getProductsByUserId(userId);
        return Product.createProductsDto(products);
    }

    @PostMapping("/user/{userId}/product/{productId}/sum/{sum}")
    public PaymentDto doPayment(@PathVariable Long userId,
                                @PathVariable Long productId,
                                @PathVariable BigDecimal sum){
        return productService.doPayment(userId, productId, sum);
    }
}

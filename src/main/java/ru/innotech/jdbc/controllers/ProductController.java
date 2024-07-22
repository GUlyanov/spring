package ru.innotech.jdbc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.innotech.jdbc.dto.ProductDto;
import ru.innotech.jdbc.dto.ProductsDto;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.servicies.ProductService;
import java.util.Set;

@RestController("productController")
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

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> productById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        ProductDto productDto = new ProductDto(product);
        return ResponseEntity
                .status(200)
                .body(productDto);
    }

    @GetMapping("/products/{userId}")
    public ProductsDto productsByUserId(@PathVariable Long userId) {
        Set<Product> products = productService.getProductsByUserId(userId);
        return new ProductsDto(products);
    }
}

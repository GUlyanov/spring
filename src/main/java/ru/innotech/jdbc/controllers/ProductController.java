package ru.innotech.jdbc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.innotech.jdbc.dto.ProductDto;
import ru.innotech.jdbc.dto.ProductsDto;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.servicies.ProductService;

import java.util.Optional;
import java.util.Set;

@RestController("productController")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
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
    public ResponseEntity<ProductsDto> productsByUserId(@PathVariable Long userId) {
        Set<Product> products = productService.getProductsByUserId(userId);
        ProductsDto productsDto = new ProductsDto(products);
        return ResponseEntity
                .status(200)
                .body(productsDto);
    }
}

package ru.innotech.products.repositories;

import ru.innotech.products.entities.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductRepo {
    Set<Product> getProductsByUserId(Long userId);

    Optional<Product> getProductById(Long productId);

    void insert(Product product, Long userId);

    void update(Product product);

    void delete(Product product);

    void deleteAll();

    Set<Product> findAll();

    Boolean productIsAccessed(Long userId, Long productId);
}

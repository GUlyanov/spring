package ru.innotech.jdbc.repositories;

import ru.innotech.jdbc.entities.Product;
import java.util.Optional;
import java.util.Set;

public interface ProductDao {
    Set<Product> getProductsByUserId(Long userId);
    Optional<Product> getProductById(Long productId);
    void insert(Product product, Long userId);

    void update(Product product);

    void delete(Product product);

    void deleteAll();
    Set<Product> findAll();
}

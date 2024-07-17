package ru.innotech.jdbc.repositories;

import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.entities.User;

import java.util.Optional;
import java.util.Set;

public interface ProductDao {
    public Set<Product> getProductsByUserId(Long userId);
    public Optional<Product> getProductById(Long productId);
    void insert(Product product, Long userId);

    void update(Product product);

    void delete(Product product);

    void deleteAll();
    Set<Product> findAll();
}

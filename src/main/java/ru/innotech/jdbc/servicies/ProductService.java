package ru.innotech.jdbc.servicies;

import ru.innotech.jdbc.dto.ProductDto;
import ru.innotech.jdbc.dto.ProductsDto;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.repositories.ProductDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {
    Product getProductById(Long productId);
    Set<Product> getProductsByUserId(Long userId);
    void insert(Product product, Long userId);
    void update(Product product);
    void delete(Product product);
    void deleteAll();
    Set<Product> findAll();
}

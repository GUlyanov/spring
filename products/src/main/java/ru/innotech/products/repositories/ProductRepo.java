package ru.innotech.products.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.innotech.products.entities.Product;

import java.util.Set;

public interface ProductRepo extends CrudRepository<Product, Long> {
    Set<Product> findByUserId(Long userId);

    Set<Product> findAll();

    @Query("select p.user.id = ?1 from Product p where p.id = ?2")
    Boolean productIsAccessed(Long userId, Long productId);
}

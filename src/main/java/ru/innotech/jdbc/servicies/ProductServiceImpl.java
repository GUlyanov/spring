package ru.innotech.jdbc.servicies;

import org.springframework.stereotype.Service;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.exceptions.ProductByIdNotFoundException;
import ru.innotech.jdbc.exceptions.ProductsByUserIdNotFoundException;
import ru.innotech.jdbc.repositories.ProductDao;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProductById(Long productId){
        Optional<Product> optionalProduct = productDao.getProductById(productId);
        if (optionalProduct.isEmpty())
            throw new ProductByIdNotFoundException(null, productId);
        return optionalProduct.get();
    }
    public Set<Product> getProductsByUserId(Long userId){
        Set<Product> productSet = productDao.getProductsByUserId(userId);
        if (productSet.isEmpty())
            throw new ProductsByUserIdNotFoundException(null, userId);
        return productSet;
    }

    @Override
    public void insert(Product product, Long userId) {
        productDao.insert(product, userId);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
    }

    @Override
    public Set<Product> findAll() {
        return productDao.findAll();
    }

}

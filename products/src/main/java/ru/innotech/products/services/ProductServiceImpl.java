package ru.innotech.products.services;

import org.springframework.stereotype.Service;
import ru.innotech.dtos.dto.PaymentReqDto;
import ru.innotech.dtos.dto.PaymentRespDto;
import ru.innotech.products.entities.Product;
import ru.innotech.products.exceptions.ProductAccInsufficientFundsException;
import ru.innotech.products.exceptions.ProductAccessDeniedException;
import ru.innotech.products.exceptions.ProductByIdNotFoundException;
import ru.innotech.products.exceptions.ProductsByUserIdNotFoundException;
import ru.innotech.products.repositories.ProductRepo;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productDao) {
        this.productRepo = productDao;
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepo.getProductById(productId);
        if (optionalProduct.isEmpty())
            throw new ProductByIdNotFoundException(null, productId);
        return optionalProduct.get();
    }

    public Set<Product> getProductsByUserId(Long userId) {
        Set<Product> productSet = productRepo.getProductsByUserId(userId);
        if (productSet.isEmpty())
            throw new ProductsByUserIdNotFoundException(null, userId);
        return productSet;
    }

    @Override
    public void insert(Product product, Long userId) {
        productRepo.insert(product, userId);
    }

    @Override
    public void update(Product product) {
        productRepo.update(product);
    }

    @Override
    public void delete(Product product) {
        productRepo.delete(product);
    }

    @Override
    public void deleteAll() {
        productRepo.deleteAll();
    }

    @Override
    public Set<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Boolean productIsAccessed(Long userId, Long productId) {
        return productRepo.productIsAccessed(userId, productId);
    }

    public PaymentRespDto doPayment(PaymentReqDto payReqDto) {
        Long userId = payReqDto.getUserId();
        Long productId = payReqDto.getProductId();
        BigDecimal sum = payReqDto.getSum();

        // 1 Получить информацию по продукту на предмет авторизации
        Product product = getProductById(productId);

        // Проверка 1. Продукт с заданным ид существует
        // запрос выше выдаст исключение, если не существует договор

        // Проверка 2. Продукт принадлежит текущему пользователю
        if (!productIsAccessed(userId, productId)) {
            throw new ProductAccessDeniedException(null, userId, productId);
        }

        // Проверка 3. Остаток на счете больше или равен сумме платежа
        BigDecimal rest = product.getAccRest();
        if (rest == null || rest.compareTo(sum) < 0) {
            throw new ProductAccInsufficientFundsException(null, product.getId(), userId, rest);
        }

        // Списать сумму со счета продукта пользователя
        BigDecimal oldRest = product.getAccRest();
        BigDecimal newRest = rest.subtract(sum);
        product.setAccRest(newRest);
        productRepo.update(product);

        // Сформировать ответ
        return new PaymentRespDto(userId, productId, sum, oldRest, newRest);
    }

}

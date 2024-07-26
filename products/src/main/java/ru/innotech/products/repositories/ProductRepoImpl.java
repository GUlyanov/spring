package ru.innotech.products.repositories;

import org.springframework.stereotype.Repository;
import ru.innotech.products.entities.Product;
import ru.innotech.products.entities.ProductType;
import ru.innotech.products.exceptions.RepoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository("productDao")
public class ProductRepoImpl implements ProductRepo {
    private final Connection connection;

    public ProductRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Set<Product> getProductsByUserId(Long userId) {
        Set<Product> result = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.FIND_PRODUCTS_BY_USER_ID)) {
            statement.setLong(1, userId);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setAccNumber(resultSet.getString("accNumber"));
                product.setAccRest(resultSet.getBigDecimal("accRest"));
                String type = resultSet.getString("prodType");
                product.setProdType(ProductType.valueOf(type));
                result.add(product);
            }
        } catch (SQLException ex) {
            throw new RepoException("Проблема список продуктов клиента: " + ex.getMessage());
        }

        return result;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        try (var statement = connection.prepareStatement(QueryConstants.FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, productId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var product = new Product();
                product.setId(productId);
                product.setAccNumber(resultSet.getString("accNumber"));
                product.setAccRest(resultSet.getBigDecimal("accRest"));
                String type = resultSet.getString("prodType");
                product.setProdType(ProductType.valueOf(type));
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RepoException("Проблема поиска продукта по Id: " + ex.getMessage());
        }
    }

    @Override
    public void insert(Product product, Long userId) {
        try (var statement = connection.prepareStatement(QueryConstants.INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getAccNumber());
            statement.setBigDecimal(2, product.getAccRest());
            statement.setString(3, product.getProdType().toString());
            statement.setLong(4, userId);
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            throw new RepoException("Проблема вставки продукта: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Product product) {
        try (var statement = connection.prepareStatement(QueryConstants.DELETE_PRODUCT)) {
            statement.setLong(1, product.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Проблема удаления продукта: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (var statement = connection.prepareStatement(QueryConstants.DELETE_ALL_PRODUCTS)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Проблема при удалении всех продуктов: " + ex.getMessage());
        }
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> result = new HashSet<>();
        try (var statement = connection.prepareStatement(QueryConstants.SELECT_ALL_PRODUCTS);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setAccNumber(resultSet.getString("accNumber"));
                product.setAccRest(resultSet.getBigDecimal("accRest"));
                String type = resultSet.getString("prodType");
                product.setProdType(ProductType.valueOf(type));
                result.add(product);
            }
        } catch (SQLException ex) {
            throw new RepoException("Проблема поиска всех продуктов: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public Boolean productIsAccessed(Long userId, Long productId) {
        try (var statement = connection.prepareStatement(QueryConstants.PRODUCT_IS_ACCESS)) {
            statement.setLong(1, userId);
            statement.setLong(2, productId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("res");
            }
            return null;
        } catch (SQLException ex) {
            throw new RepoException("Проблема поиска продукта по Id: " + ex.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        try (var statement = connection.prepareStatement(QueryConstants.UPDATE_PRODUCT)) {
            statement.setString(1, product.getAccNumber());
            statement.setBigDecimal(2, product.getAccRest());
            statement.setString(3, product.getProdType().toString());
            statement.setLong(4, product.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Проблема модификации продукта: " + ex.getMessage());
        }
    }

}

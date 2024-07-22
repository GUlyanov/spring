package ru.innotech.jdbc.repositories;

import org.springframework.stereotype.Repository;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.entities.ProductType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ru.innotech.jdbc.repositories.QueryConstants.*;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{
    private final Connection connection;
    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Set<Product> getProductsByUserId(Long userId){
        Set<Product> result = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_USER_ID)){
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
            throw new RuntimeException("Проблема список продуктов клиента: " + ex.getMessage());
        }

        return result;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        try (var statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)){
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
            throw new RuntimeException("Проблема поиска продукта по Id: " + ex.getMessage());
        }
    }

    @Override
    public void insert(Product product, Long userId) {
        try ( var statement = connection.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)){
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
            throw new RuntimeException("Проблема вставки продукта: " + ex.getMessage());
        }
    }
    @Override
    public void delete(Product product) {
        try (var statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setLong(1, product.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема удаления продукта: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (var statement = connection.prepareStatement(DELETE_ALL_PRODUCTS)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема при удалении всех продуктов: " + ex.getMessage());
        }
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> result = new HashSet<>();
        try (var statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
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
            throw new RuntimeException("Проблема поиска всех продуктов: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public void update(Product product) {
        try (var statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getAccNumber());
            statement.setBigDecimal(2, product.getAccRest());
            statement.setString(3, product.getProdType().toString());
            statement.setLong(4, product.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема модификации продукта: " + ex.getMessage());
        }
    }

}

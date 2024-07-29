package ru.innotech.products.repositories;

import org.springframework.stereotype.Repository;
import ru.innotech.products.entities.Product;
import ru.innotech.products.entities.User;
import ru.innotech.products.exceptions.RepoException;
import ru.innotech.products.exceptions.UserByIdNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository("userDao")
public class UserRepoImpl implements UserRepo {

    private final Connection connection;
    private final ProductRepo productDao;

    public UserRepoImpl(Connection connection, ProductRepo productDao) {
        this.connection = connection;
        this.productDao = productDao;
    }

    @Override
    public Set<User> findAll() {
        Set<User> result = new HashSet<>();
        try (var statement = connection.prepareStatement(QueryConstants.SELECT_ALL_USERS);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("username"));
                Set<Product> productSet = productDao.getProductsByUserId(user.getId());
                user.setProductSet(productSet);
                result.add(user);
            }
        } catch (SQLException ex) {
            throw new RepoException("Проблема поиска всех пользователей: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public void insert(User user) {
        try (var statement = connection.prepareStatement(QueryConstants.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            for (Product product : user.getProductSet()) {
                productDao.insert(product, user.getId());
            }
        } catch (SQLException ex) {
            throw new RepoException("Проблема вставки пользователя: " + ex.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        for (Product product : user.getProductSet()) {
            productDao.delete(product);
        }
        try (var statement = connection.prepareStatement(QueryConstants.DELETE_USER)) {
            statement.setLong(1, user.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Проблема удаления пользователя: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Long userId) {
        Optional<User> optionalUser = findById(userId);
        User user = optionalUser.orElseThrow(() -> new UserByIdNotFoundException(null, userId));
        delete(user);
    }

    @Override
    public void update(User user) {
        try (var statement = connection.prepareStatement(QueryConstants.UPDATE_USER)) {
            statement.setString(1, user.getUserName());
            statement.setLong(2, user.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Проблема модификации пользователя: " + ex.getMessage());
        }
    }

    @Override
    public Set<User> findByName(String userName) {
        Set<User> result = new HashSet<>();
        try (var statement = connection.prepareStatement(QueryConstants.FIND_USER_BY_NAME)) {
            statement.setString(1, userName);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("username"));
                Set<Product> productSet = productDao.getProductsByUserId(user.getId());
                user.setProductSet(productSet);
                result.add(user);
            }
        } catch (SQLException ex) {
            throw new RepoException("Проблема поиска пользователя по имени: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var statement = connection.prepareStatement(QueryConstants.FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var user = new User();
                user.setId(id);
                user.setUserName(resultSet.getString("username"));
                Set<Product> productSet = productDao.getProductsByUserId(user.getId());
                user.setProductSet(productSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RepoException("Проблема поиска пользователя по Id: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
        try (var statement = connection.prepareStatement(QueryConstants.DELETE_ALL_USERS)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Проблема при удалении всех пользователей: " + ex.getMessage());
        }
    }

}

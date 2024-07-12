package ru.innotech.jdbc.repositories;

import org.springframework.stereotype.Repository;
import ru.innotech.jdbc.entities.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ru.innotech.jdbc.repositories.QueryConstants.*;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<User> findAll() {
        Set<User> result = new HashSet<>();
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(ALL_SELECT);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("username"));
                result.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема при выполнении SELECT: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public void insert(User user) {
        try ( var connection = dataSource.getConnection();
            var statement = connection.prepareStatement(SIMPLE_INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getUserName());
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема при выполнении INSERT: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Long userId) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(SIMPLE_DELETE)) {
            statement.setLong(1, userId);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема при выполнении DELETE: " + ex.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUserName());
            statement.setLong(2, user.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема при выполнении UPDATE: " + ex.getMessage());
        }
    }

    @Override
    public Set<User> findByName(String userName) {
        Set<User> result = new HashSet<>();
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(FIND_BY_NAME)){
             statement.setString(1, userName);
             var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("username"));
                result.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема поиска пользователя по имени: " + ex.getMessage());
        }
        return result;
    }
    @Override
    public Optional<User> findById(Long id) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(FIND_NAME)){
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var user = new User();
                user.setId(id);
                user.setUserName(resultSet.getString("username"));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема пользователя по Id: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(DELETE_ALL)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Проблема при выполнении DELETE ALL: " + ex.getMessage());
        }
    }

}

package ru.innotech.jdbc.dao;

import ru.innotech.jdbc.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Set<User> findAll();

    Set<User> findByName(String userName);

    Optional<User> findById(Long id);

    User insert(User user);

    void update(User user);

    void delete(Long id);

    void deleteAll();
}

package ru.innotech.jdbc.repositories;

import ru.innotech.jdbc.entities.User;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Set<User> findAll();

    Set<User> findByName(String userName);

    Optional<User> findById(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

    void deleteAll();
}

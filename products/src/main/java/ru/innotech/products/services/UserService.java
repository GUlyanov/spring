package ru.innotech.products.services;

import ru.innotech.products.entities.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Set<User> findAll();

    Set<User> findByName(String userName);

    Optional<User> findById(Long id);

    void insert(User user);

    void update(User user);

    void delete(User user);

    void delete(Long userId);

    void deleteAll();
}

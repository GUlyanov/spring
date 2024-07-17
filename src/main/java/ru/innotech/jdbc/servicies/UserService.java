package ru.innotech.jdbc.servicies;

import ru.innotech.jdbc.entities.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    public Set<User> findAll();
    public Set<User> findByName(String userName);
    public Optional<User> findById(Long id);
    public void insert(User user);
    public void update(User user);
    public void delete(User user);
    public void delete(Long userId);
    public void deleteAll();
}

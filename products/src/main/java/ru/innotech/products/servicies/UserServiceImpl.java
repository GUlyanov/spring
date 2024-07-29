package ru.innotech.products.servicies;

import org.springframework.stereotype.Service;
import ru.innotech.products.entities.User;
import ru.innotech.products.repositories.UserRepo;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userDao) {
        this.userRepo = userDao;
    }

    public Set<User> findAll() {
        return userRepo.findAll();
    }

    public Set<User> findByName(String userName) {
        return userRepo.findByName(userName);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public void insert(User user) {
        userRepo.insert(user);
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void delete(Long userId) {
        userRepo.delete(userId);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }
}

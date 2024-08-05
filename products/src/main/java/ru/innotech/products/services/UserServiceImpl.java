package ru.innotech.products.services;

import org.springframework.stereotype.Service;
import ru.innotech.products.entities.User;
import ru.innotech.products.repositories.UserRepo;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Set<User> findAll() {
        return userRepo.findAll();
    }

    public Set<User> findByName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }
}

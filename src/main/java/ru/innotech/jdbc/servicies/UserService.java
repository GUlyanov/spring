package ru.innotech.jdbc.servicies;

import org.springframework.stereotype.Service;
import ru.innotech.jdbc.entities.User;
import ru.innotech.jdbc.repositories.UserDao;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Set<User> findAll(){
        return userDao.findAll();
    }

    public Set<User> findByName(String userName){
        return userDao.findByName(userName);
    }

    public Optional<User> findById(Long id){
        return userDao.findById(id);
    }

    public void insert(User user){
        userDao.insert(user);
    }

    public void update(User user){
        userDao.update(user);
    }

    public void delete(Long id){
        userDao.delete(id);
    }
    public void deleteAll(){
        userDao.deleteAll();
    }

}

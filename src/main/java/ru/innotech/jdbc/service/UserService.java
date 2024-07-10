package ru.innotech.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innotech.jdbc.entity.User;
import ru.innotech.jdbc.dao.UserDao;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Set<User> findAll(){
        return userDao.findAll();
    }

    public Set<User> findByName(String userName){
        return userDao.findByName(userName);
    }

    public Optional<User> findById(Long id){
        return userDao.findById(id);
    }

    public User insert(User user){
        return userDao.insert(user);
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

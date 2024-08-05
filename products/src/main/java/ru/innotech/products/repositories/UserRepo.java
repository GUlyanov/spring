package ru.innotech.products.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.innotech.products.entities.User;

import java.util.Set;

public interface UserRepo extends CrudRepository<User, Long> {

    Set<User> findByUserName(String userName);

    @Override
    Set<User> findAll();

}

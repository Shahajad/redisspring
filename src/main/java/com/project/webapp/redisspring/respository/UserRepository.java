package com.project.webapp.redisspring.respository;

import com.project.webapp.redisspring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    User findByEmailId(String email);

    List<User> findAll();

}

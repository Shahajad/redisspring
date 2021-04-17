package com.project.webapp.redisspring.service;

import com.project.webapp.redisspring.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User saveUser(User user);

    List<User> fetchAllUser();

    Optional<User> fetchUserById(String id);

    User fetchUserByEmail(String email);
}

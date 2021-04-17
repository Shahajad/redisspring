package com.project.webapp.redisspring.service;

import com.project.webapp.redisspring.model.User;
import com.project.webapp.redisspring.respository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> fetchUserById(String id) {
        return userRepository.findById(id);
    }


    @Override
    public User fetchUserByEmail(String email){
        return userRepository.findByEmailId(email);
    }
}

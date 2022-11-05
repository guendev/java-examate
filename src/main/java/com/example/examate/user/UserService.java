package com.example.examate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        Optional<User> optional = repository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new UserNotFoundException("User not found");
    }
}

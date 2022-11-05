package com.example.examate;

import com.example.examate.user.User;
import com.example.examate.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repository;

    @Test
    public void addNewUser() {
        User user = new User();
        user.setEmail("dnstylish@gmail.com");
        user.setFirstName("Nguyen");
        user.setLastName("Tran");
        User saveUser = repository.save(user);

        Assertions.assertNotNull(saveUser);
    }

    @Test
    public void listAllUsers() {
        Iterable<User> users = repository.findAll();
        Assertions.assertNotNull(users);

        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repository.findById(userId);

        User user = optionalUser.get();
        user.setLastName("Koki");
        repository.save(user);
    }

    @Test
    public void testDelete() {
        Integer userId = 1;
        repository.deleteById(userId);
    }

    @Test
    public void getUserById() {
        Integer userId = 1;
        Optional<User> optionalUser = repository.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());
    }

    @Test
    public void removeUserById() {
        Integer userId = 1;
        repository.deleteById(userId);
    }
}

package com.example.examate;

import com.example.examate.user.User;
import com.example.examate.user.UserNotFoundException;
import com.example.examate.user.UserRepository;
import com.example.examate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService uservice;

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("users", repository.findAll());
        return "index";
    }

    @PostMapping("/api/customer")
    @ResponseBody
    public String addCustomer(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");

        if(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            return "Please fill all fields";
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        User saveUser = repository.save(user);

        return saveUser.getId().toString();
    }

    @PutMapping("/api/customer/{id}")
    @ResponseBody
    public String updateCustomer(@RequestBody Map<String, String> body, @PathVariable String id) {
        try {
            User user = uservice.getUserById(Integer.parseInt(id));

            String email = body.get("email");
            String firstName = body.get("firstName");
            String lastName = body.get("lastName");

            if(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                return "Please fill all fields";
            }

            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            User saveUser = repository.save(user);
            return saveUser.toString();
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/api/customer/{id}")
    @ResponseBody
    public String deleteUserById(@PathVariable String id) {
        try {
            User user = uservice.getUserById(Integer.parseInt(id));
            repository.delete(user);
            return "User deleted";
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }
}


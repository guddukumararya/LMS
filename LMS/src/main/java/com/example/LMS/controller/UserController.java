package com.example.LMS.controller;

import com.example.LMS.model.User;
import com.example.LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> allUsers() {

        return userService.findAll();
    }
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}

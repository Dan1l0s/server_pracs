package com.ru.prac7.auth.controller;

import com.ru.prac7.auth.model.User;
import com.ru.prac7.auth.repository.RedisRepository;
import com.ru.prac7.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    public Map<Object, Object> keys() {
        return userService.findAll();
    }

    @PostMapping("")
    public String addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("/{login}")
    public User findUser(@PathVariable String login) {
        return userService.find(login);
    }

    @PutMapping("/{login}")
    public String updateUser(@PathVariable String login, @RequestBody User user) {
        return userService.update(login, user);
    }

    @DeleteMapping("/{login}")
    public String deleteUser(@PathVariable String login) {
        return userService.delete(login);
    }

}

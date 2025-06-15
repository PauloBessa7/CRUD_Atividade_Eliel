package com.br.uSafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.uSafe.model.User;
import com.br.uSafe.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping()
    public List<User> findUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUserId(id);
    }

}




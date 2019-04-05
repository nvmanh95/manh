package com.manh596.user.controller;

import com.manh596.user.model.User;
import com.manh596.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/profile/{userName}")
    public User getUser(@RequestParam String userName) {
        Optional<User> userByUserName = userService.getUserByUserName(userName);
        System.out.println(userName);
        return userByUserName.orElse(null);
    }
}
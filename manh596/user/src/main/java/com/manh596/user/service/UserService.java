package com.manh596.user.service;

import com.manh596.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void create(User newUser);

    void delete(User toBeDeleted);

    void update(User toBeUpdated);

    List<User> getAll();

    Optional<User> getUserById(String userId);

    Optional<User> getUserByUserName(String userName);

    Optional<User> getUserByEmail(String email);
}
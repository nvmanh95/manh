package com.manh596.user.service.impl;

import com.manh596.common.service.NumberService;
import com.manh596.user.model.User;
import com.manh596.user.repository.UserRepository;
import com.manh596.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static String USER_ID_PREFIX = "usr";

    @Autowired
    @Qualifier("userRepository")
    private UserRepository<User, String> repository;

    @Autowired
    private NumberService numberService;

    @Override
    public void create(User newUser) {
        newUser.setId(USER_ID_PREFIX + numberService.generateId());
        repository.addNewUser(newUser);
    }

    @Override
    public void delete(User toBeDeleted) {
        repository.removeUser(toBeDeleted);
    }

    @Override
    public void update(User toBeUpdated) {
        repository.update(toBeUpdated);
    }

    @Override
    public List<User> getAll() {
        return repository.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return Optional.of(repository.getUserById(userId));
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return Optional.of(repository.getUserByUserName(userName));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.of(repository.getUserByEmail(email));
    }
}
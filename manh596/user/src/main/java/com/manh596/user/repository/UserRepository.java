package com.manh596.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface UserRepository<D, K extends Serializable> {

    Optional<D> getUserById(K userId);

    List<D> getAllUsers();

    Optional<D> getUserByEmail(D userWithEmail);

    Optional<D> getUserByUserName(D userWithName);

    void addNewUser(D newUser);

    Boolean removeUser(D toBeRemoved);

    Boolean update(K currentId, D toBeUpdated);
}
package com.manh596.user.repository;

import java.io.Serializable;
import java.util.List;

public interface UserRepository<D, K extends Serializable> {

    D getUserById(K userId);

    List<D> getAllUsers();

    D getUserByEmail(K userEmail);

    D getUserByUserName(K userName);

    void addNewUser(D newUser);

    Boolean removeUser(D toBeRemoved);

    Boolean update(D toBeUpdated);
}
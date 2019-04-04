package com.manh596.user.repository.impl;

import com.manh596.common.repository.mongo.locker.ReadWriteLocker;
import com.manh596.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl<D, K extends Serializable> implements UserRepository<D, K> {

    private SimpleMongoRepository<D, K> primaryRepository;

    private SimpleMongoRepository<D, K> secondaryRepository;

    private SimpleMongoRepository<D, K> actualSource;

    @Autowired
    private ReadWriteLocker readWriteLocker;

    public UserRepositoryImpl(SimpleMongoRepository<D, K> primaryRepository, SimpleMongoRepository<D, K> secondaryRepository) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;
        actualSource = primaryRepository;
    }

    @Override
    public Optional<D> getUserById(K userId) {
        return readWriteLocker.readLock(() -> actualSource.findById(userId));
    }

    @Override
    public List<D> getAllUsers() {
        return readWriteLocker.readLock(() -> actualSource.findAll());
    }

    @Override
    public Optional<D> getUserByEmail(D userWithEmail) {
        return readWriteLocker.readLock(() -> actualSource.findOne(Example.of(userWithEmail)));
    }

    @Override
    public Optional<D> getUserByUserName(D userWithName) {
        return readWriteLocker.readLock(() -> actualSource.findOne(Example.of(userWithName)));
    }

    @Override
    public void addNewUser(D newUser) {
        readWriteLocker.writeBlock(() -> actualSource.save(newUser));
    }

    @Override
    public Boolean removeUser(D toBeRemoved) {
        return readWriteLocker.writeBlock(() -> {
            actualSource.delete(toBeRemoved);
            return true;
        });
    }

    @Override
    public Boolean update(K currentId, D toBeUpdated) {
        return readWriteLocker.writeBlock(() -> {
            actualSource.deleteById(currentId);
            actualSource.save(toBeUpdated);
            return true;
        });
    }
}
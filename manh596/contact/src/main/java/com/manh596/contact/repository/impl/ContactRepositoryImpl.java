package com.manh596.contact.repository.impl;

import com.manh596.common.repository.mongo.locker.ReadWriteLocker;
import com.manh596.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ContactRepositoryImpl<D, K extends Serializable> implements ContactRepository<D, K> {
    private SimpleMongoRepository<D, K> primaryRepository;

    private SimpleMongoRepository<D, K> secondaryRepository;

    private SimpleMongoRepository<D, K> actualSource;

    @Autowired
    private ReadWriteLocker readWriteLocker;

    public ContactRepositoryImpl(SimpleMongoRepository<D, K> primaryRepository, SimpleMongoRepository<D, K> secondaryRepository) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;

        this.actualSource = primaryRepository;
    }

    @Override
    public Optional<D> getById(K id) {
        return readWriteLocker.readLock(() -> actualSource.findById(id));
    }

    @Override
    public List<D> getAll() {
        return readWriteLocker.readLock(() -> actualSource.findAll());
    }

    @Override
    public Boolean save(D entity) {
        return readWriteLocker.writeBlock(() -> {
            actualSource.save(entity);
            return true;
        });
    }

    @Override
    public Boolean update(K idEntityToBeUpdated, D entity) {
        return null;
    }

    @Override
    public void delete(D toBeDeleted) {
        readWriteLocker.writeBlock(() -> {
            actualSource.delete(toBeDeleted);
            return true;
        });
    }

    @Override
    public void deleteAll() {
        readWriteLocker.writeBlock(() -> {
            actualSource.deleteAll();
            return true;
        });
    }

    @Override
    public void saveAll(List<D> contacts) {
        readWriteLocker.writeBlock(() -> {
            actualSource.insert(contacts);
            return true;
        });
    }
}
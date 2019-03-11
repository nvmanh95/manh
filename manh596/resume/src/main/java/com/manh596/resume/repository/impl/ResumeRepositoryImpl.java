package com.manh596.resume.repository.impl;

import com.manh596.resume.repository.ReadWriteLocker;
import com.manh596.resume.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;

public class ResumeRepositoryImpl<D, K extends Serializable> implements ResumeRepository<D, K> {

    private SimpleMongoRepository<D, K> primaryRepository;

    private SimpleMongoRepository<D, K> secondaryRepository;

    private SimpleMongoRepository<D, K> actualSource;

    @Autowired
    private ReadWriteLocker readWriteLocker;

    public ResumeRepositoryImpl(SimpleMongoRepository<D, K> primaryRepository, SimpleMongoRepository<D, K> secondaryRepository) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;
        actualSource = primaryRepository;
    }

    @Override
    public D getById(K id) {
        return readWriteLocker.readLock(() -> actualSource.findOne(id));
    }

    @Override
    public List<D> getAll() {
        return readWriteLocker.readLock(() -> actualSource.findAll());
    }

    @Override
    public Boolean save(D entity) {
        return readWriteLocker.writeBlock(() -> actualSource.save(entity)) != null;
    }

    @Override
    public Boolean update(K idEntityToBeUpdated, D entity) {
        return null;// readWriteLocker.writeBlock(() -> actualSource);
    }

    @Override
    public void delete(K idEntityToBeDeleted) {
        readWriteLocker.writeBlock(() -> {
            actualSource.delete(idEntityToBeDeleted);
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
    public void saveAll(List<D> educations) {
        readWriteLocker.writeBlock(() -> {
            actualSource.save(educations);
            return true;
        });
    }
}
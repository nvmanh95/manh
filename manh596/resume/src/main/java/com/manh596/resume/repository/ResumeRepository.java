package com.manh596.resume.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ResumeRepository<D, K extends Serializable> {
    Optional<D> getById(K id);

    List<D> getAll();

    Boolean save(D entity);

    Boolean update(K idEntityToBeUpdated, D entity);

    void delete(D toBeDeleted);

    void deleteAll();

    void saveAll(List<D> educations);
}
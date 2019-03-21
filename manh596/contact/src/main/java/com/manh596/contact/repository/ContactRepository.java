package com.manh596.contact.repository;

import java.io.Serializable;
import java.util.List;

public interface ContactRepository <D, K extends Serializable> {
    D getById(K id);

    List<D> getAll();

    Boolean save(D entity);

    Boolean update(K idEntityToBeUpdated, D entity);

    void delete(K idEntityToBeDeleted);

    void deleteAll();

    void saveAll(List<D> contacts);
}
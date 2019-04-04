package com.manh596.resume.service;

import com.manh596.resume.model.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    List<Experience> getExperiences();

    Optional<Experience> getById(String id);

    void delete(Experience idToBeDeleted);

    void deleteAll();

    void add(Experience experience);

    void addAll(List<Experience> experiences);

    void update(String toBeUpdated, Experience experience);
}

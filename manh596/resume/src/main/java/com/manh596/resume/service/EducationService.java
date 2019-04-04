package com.manh596.resume.service;

import com.manh596.resume.model.Education;

import java.util.List;
import java.util.Optional;

public interface EducationService {

    List<Education> getEducations();

    Optional<Education> getById(String id);

    void delete(Education idToBeDeleted);

    void deleteAll();

    void add(Education education);

    void addAll(List<Education> educations);

    void update(String toBeUpdated, Education education);
}
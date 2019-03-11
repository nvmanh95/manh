package com.manh596.resume.service;

import com.manh596.resume.model.Education;

import java.util.List;

public interface EducationService {

    List<Education> getEducations();

    Education getById(String id);

    void delete(String idToBeDeleted);

    void deleteAll();

    void add(Education education);

    void addAll(List<Education> educations);

    void update(String toBeUpdated, Education education);
}
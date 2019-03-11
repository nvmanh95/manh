package com.manh596.resume.service.impl;

import com.manh596.resume.model.Education;
import com.manh596.resume.repository.ResumeRepository;
import com.manh596.resume.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    @Qualifier("educationRepository")
    private ResumeRepository<Education, String> repository;

    @Override
    public List<Education> getEducations() {
        return repository.getAll();
    }

    @Override
    public Education getById(String id) {
        return repository.getById(id);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void add(Education education) {
        repository.save(education);
    }

    @Override
    public void addAll(List<Education> educations) {
        repository.saveAll(educations);
    }

    @Override
    public void update(String toBeUpdated, Education education) {
        repository.update(toBeUpdated, education);
    }
}